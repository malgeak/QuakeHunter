package com.malgeak.quakehunter.ui.credentials.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.databinding.FragmentLoginBinding
import com.malgeak.quakehunter.di.Injector
import com.malgeak.quakehunter.ui.quakehunter.MainActivity
import javax.inject.Inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as Injector).createLoginSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.existUser()) {
            createObservers()
            createActions()
        } else {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }


    private fun createObservers() {
        viewModel.successLogin.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                binding.textViewMessage.visibility = View.VISIBLE
            }
        }
    }

    private fun createActions() {
        binding.buttonLogin.setOnClickListener {
            viewModel.login(
                binding.textFieldUser.editText?.text.toString(),
                binding.textFieldPassword.editText?.text.toString()
            )
        }
    }


}
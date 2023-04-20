package com.malgeak.quakehunter.ui.credentials.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.databinding.FragmentSignUpBinding
import com.malgeak.quakehunter.di.Injector
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as Injector).createSignUpSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[SignupViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createObservers()
        createActions()
    }

    private fun createObservers() {
        viewModel.successSign.observe(viewLifecycleOwner) {
            if (it) {
                binding.root.findNavController().popBackStack()
            }
        }

        viewModel.usernameError.observe(viewLifecycleOwner) {
            if (!it) {
                binding.textFieldUser.error = getString(R.string.err_msg_invalid_username)
            } else {
                binding.textFieldUser.error = null
            }
        }

        viewModel.passwordError.observe(viewLifecycleOwner) {
            if (!it) {
                binding.textFieldConfirmPassword.error =
                    getString(R.string.err_msg_invalid_password)
                binding.textViewMessage.text = getString(R.string.err_msg_invalid_password)
                binding.textViewMessage.visibility = View.VISIBLE
            } else {
                binding.textFieldConfirmPassword.error = null
                binding.textViewMessage.visibility = View.GONE
            }
        }
    }

    private fun createActions() {
        binding.buttonSign.setOnClickListener {
            viewModel.signUp(
                binding.textFieldUser.editText?.text.toString(),
                binding.textFieldPassword.editText?.text.toString(),
                binding.textFieldConfirmPassword.editText?.text.toString()
            )
        }
    }
}
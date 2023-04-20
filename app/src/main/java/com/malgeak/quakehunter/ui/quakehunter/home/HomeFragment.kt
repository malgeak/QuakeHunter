package com.malgeak.quakehunter.ui.quakehunter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.databinding.FragmentHomeBinding
import com.malgeak.quakehunter.di.Injector
import com.malgeak.quakehunter.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var datePicker: MaterialDatePicker<Long>

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as Injector).createHomeSubComponent().inject(this)

        mViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

    }

    override fun onResume() {
        setTitle(getString(R.string.select_date_title))
        mViewModel.isLastQueryAvaible()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        createDatePicker()
        createObservers()
        createActions()

        return binding.root
    }

    private fun createObservers() {
        mViewModel.dateChanged.observe(viewLifecycleOwner) {
            setDate(it.first, it.second)
        }
    }

    private fun createActions() {
        binding.buttonDatePicker.setOnClickListener {
            datePicker.show(childFragmentManager, "Date")
        }

        datePicker.addOnPositiveButtonClickListener {
            mViewModel.selectDate(it)
        }

        binding.buttonQuery.setOnClickListener {
            if (mViewModel.isValidDate()) {
                val bundle = bundleOf("date" to mViewModel.dateChanged.value?.first)
                binding.root.findNavController()
                    .navigate(R.id.action_homeFragment_to_listFragment, bundle)
            } else {
                showDecisionDialog(getString(R.string.err_msg_invalid_date))
            }
        }

        binding.buttonLastQuery.setOnClickListener {
            if (mViewModel.existLastRequest()) {
                val bundle = bundleOf("lastQuery" to true)
                binding.root.findNavController()
                    .navigate(R.id.action_homeFragment_to_listFragment, bundle)
            } else {
                mViewModel.setAlertMessage(getString(R.string.err_msg_no_data))
            }
        }
    }

    private fun createDatePicker() {
        datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.select_date_title))
                .setCalendarConstraints(mViewModel.constraintDate)
                .build()
    }

    private fun setDate(dateString: String, dateTimeStamp: Long) {
        binding.textFieldDate.editText?.setText(dateString)
        binding.calendarViewSelector.date = dateTimeStamp
    }

}
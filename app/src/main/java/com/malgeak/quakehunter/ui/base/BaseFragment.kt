package com.malgeak.quakehunter.ui.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.ui.quakehunter.MainActivity

open class BaseFragment<M : BaseViewModel> : Fragment() {

    protected lateinit var mViewModel: M
    private var mainActivity: MainActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = (activity as MainActivity)
        createMainObservers()
    }

    fun createMainObservers() {
        mViewModel.loader.observe(viewLifecycleOwner) {
            mainActivity?.setLoader(it)
        }

        mViewModel.alertMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                toastMessage(it)
                mViewModel.setAlertMessage(null)
            }
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showDecisionDialog(message: String) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setMessage(message)
            .setPositiveButton(R.string.msg_acept,
                DialogInterface.OnClickListener { dialog, i ->
                    dialog.dismiss()
                }).create()

        builder.show()
    }

    fun showDecisionDialog(
        message: String,
        positiveFuntion: DialogInterface.OnClickListener,
        negativeFunction: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setMessage(message)
            .setPositiveButton(
                R.string.msg_acept,
                positiveFuntion
            )
            .setNegativeButton(
                R.string.msg_cancel,
                negativeFunction
            ).create()

        builder.show()
    }

    fun setTitle(title: String) {
        mainActivity?.setTitleToolBar(title)
    }

    fun showLoader(boolean: Boolean) {
        mainActivity?.setLoader(boolean)
    }
}

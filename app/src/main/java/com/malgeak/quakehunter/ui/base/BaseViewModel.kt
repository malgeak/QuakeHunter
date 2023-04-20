package com.malgeak.quakehunter.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _loader: MutableLiveData<Boolean> = MutableLiveData()
    private val _alertMessage: MutableLiveData<String?> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader
    val alertMessage: LiveData<String?> = _alertMessage


    fun setAlertMessage(message: String?) {
        _alertMessage.value = message
    }

    fun setLoader(boolean: Boolean) {
        _loader.value = boolean
    }

}
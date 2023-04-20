package com.malgeak.quakehunter.ui.quakehunter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.malgeak.quakehunter.data.model.EarthQuake
import com.malgeak.quakehunter.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    private val _earthQuake: MutableLiveData<EarthQuake> = MutableLiveData()
    private val _errorNoData: MutableLiveData<Boolean> = MutableLiveData()
    private val _mapReady: MutableLiveData<Boolean> = MutableLiveData()
    val earthQuake: LiveData<EarthQuake> = _earthQuake
    val errorNoData: LiveData<Boolean> = _errorNoData
    val mapReady: LiveData<Boolean> = _mapReady
    var title: String = ""
    var lat: Double = 0.0
    var lon: Double = 0.0

    fun setEarthquake(earthQuake: String) {
        if (earthQuake.isNullOrBlank()) {
            _errorNoData.value = true
            return
        } else {
            val earthQuakeObj: EarthQuake =
                Gson().fromJson<EarthQuake>(earthQuake, EarthQuake::class.java)
            _earthQuake.value = earthQuakeObj
        }
    }

    fun setErrorNull() {
        _errorNoData.value = false
    }

    fun mapReady() {
        _mapReady.value = true
    }

}
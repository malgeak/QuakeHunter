package com.malgeak.quakehunter.ui.quakehunter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.malgeak.quakehunter.data.model.EarthQuake
import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import com.malgeak.quakehunter.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ListViewModel @Inject constructor(private val earthQuakeRepository: EarthQuakeRepository) :
    BaseViewModel() {

    private var _listEarthquakes: MutableLiveData<List<EarthQuake>?> = MutableLiveData()
    val listEarthQuake: LiveData<List<EarthQuake>?> = _listEarthquakes
    var dateSelected: String? = null

    fun setDateSelect(date: String?) {
        dateSelected = date
        getListEarthQuake(dateSelected)
    }

    fun getLastRequest() {
        setLoader(true)
        viewModelScope.launch {
            _listEarthquakes.value = earthQuakeRepository.getLastConsult()
        }
    }

    private fun getListEarthQuake(date: String?) {
        setLoader(true)
        if (!date.isNullOrBlank()) {
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            val formatDate = SimpleDateFormat("dd/MM/yyyy")
            val formatAPI = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZZ")
            val startDate = formatDate.parse(date)
            calendar.time = startDate
            calendar[Calendar.HOUR] = 0
            calendar[Calendar.MINUTE] = 0
            calendar[Calendar.SECOND] = 0
            val dateStartAPI = formatAPI.format(calendar.time)
            calendar[Calendar.HOUR] = 23
            calendar[Calendar.MINUTE] = 59
            calendar[Calendar.SECOND] = 59
            val dateEndAPI = formatAPI.format(calendar.time)

            viewModelScope.launch {
                _listEarthquakes.value =
                    earthQuakeRepository.getEarthquakes(dateStartAPI, dateEndAPI)
            }
        }
    }
}
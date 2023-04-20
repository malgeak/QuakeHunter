package com.malgeak.quakehunter.ui.quakehunter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import com.malgeak.quakehunter.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val earthQuakeRepository: EarthQuakeRepository) :
    BaseViewModel() {

    private val _dateChanged: MutableLiveData<Pair<String, Long>> = MutableLiveData()
    val dateChanged: LiveData<Pair<String, Long>> = _dateChanged
    var lastDateRequested: String? = null
    var constraintDate: CalendarConstraints = createConstraintDate()

    fun isValidDate(): Boolean {
        var validation = true
        if (dateChanged.value == null) {
            validation = false
        }

        return validation
    }

    fun existLastRequest(): Boolean {
        var validation = true
        if (lastDateRequested.isNullOrBlank()) {
            validation = false
        }

        return validation
    }

    fun isLastQueryAvaible() {
        runBlocking {
            val date = earthQuakeRepository.getLastDateConsult()
            if (!date?.replace(" ", "").isNullOrBlank()) {
                lastDateRequested = date
            }
        }
    }

    fun selectDate(date: Long) {
        val timeOffset = TimeZone.getDefault().getOffset(date)
        val dateUTC = date - timeOffset
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = dateUTC
        val format = SimpleDateFormat("dd/MM/yyyy")
        val dateString = format.format(calendar.time)

        _dateChanged.value = Pair(dateString, dateUTC)
    }

    private fun createConstraintDate(): CalendarConstraints {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val today = calendar.timeInMillis
        calendar[Calendar.YEAR] = calendar[Calendar.YEAR] - 5
        val dateStart = calendar.timeInMillis

        return CalendarConstraints.Builder()
            .setStart(dateStart)
            .setEnd(today)
            .setOpenAt(today)
            .setValidator(DateValidatorPointForward.from(dateStart))
            .setValidator(DateValidatorPointBackward.before(today))
            .build()
    }
}
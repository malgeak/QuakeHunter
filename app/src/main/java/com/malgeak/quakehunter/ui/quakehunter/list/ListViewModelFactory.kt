package com.malgeak.quakehunter.ui.quakehunter.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(val earthQuakeRepository: EarthQuakeRepository) :
    ViewModelProvider.Factory {

    private val repository: EarthQuakeRepository = earthQuakeRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
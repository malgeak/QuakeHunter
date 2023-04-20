package com.malgeak.quakehunter.di.quakehunter.list

import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import com.malgeak.quakehunter.ui.quakehunter.list.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @ListScope
    @Provides
    fun getListViewModelFactory(earthQuakeRepository: EarthQuakeRepository): ListViewModelFactory {
        return ListViewModelFactory(earthQuakeRepository)
    }
}
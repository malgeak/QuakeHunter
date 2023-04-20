package com.malgeak.quakehunter.di.quakehunter.home

import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import com.malgeak.quakehunter.ui.quakehunter.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun getHomeViewModelFactory(earthQuakeRepository: EarthQuakeRepository): HomeViewModelFactory {
        return HomeViewModelFactory(earthQuakeRepository)
    }
}
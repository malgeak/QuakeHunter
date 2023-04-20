package com.malgeak.quakehunter.di.quakehunter.home

import com.malgeak.quakehunter.ui.quakehunter.home.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeSubComponent
    }
}
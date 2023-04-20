package com.malgeak.quakehunter.di.quakehunter.list

import com.malgeak.quakehunter.ui.quakehunter.list.ListFragment
import dagger.Subcomponent

@ListScope
@Subcomponent(modules = [ListModule::class])
interface ListSubComponent {
    fun inject(listFragment: ListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListSubComponent
    }
}
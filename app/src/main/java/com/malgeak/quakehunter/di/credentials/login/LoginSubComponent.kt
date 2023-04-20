package com.malgeak.quakehunter.di.credentials.login

import com.malgeak.quakehunter.ui.credentials.login.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginSubComponent {
    fun inject(loginFragment: LoginFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginSubComponent
    }
}
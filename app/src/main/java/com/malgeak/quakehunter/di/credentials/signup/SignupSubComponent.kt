package com.malgeak.quakehunter.di.credentials.signup

import com.malgeak.quakehunter.ui.credentials.signup.SignUpFragment
import dagger.Subcomponent

@SignupScope
@Subcomponent(modules = [SignupModule::class])
interface SignupSubComponent {
    fun inject(signUpFragment: SignUpFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignupSubComponent
    }
}
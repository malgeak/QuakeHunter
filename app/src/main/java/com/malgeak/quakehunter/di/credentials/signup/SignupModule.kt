package com.malgeak.quakehunter.di.credentials.signup

import com.malgeak.quakehunter.data.repository.user.UserRepository
import com.malgeak.quakehunter.ui.credentials.signup.SignUpViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignupModule {

    @SignupScope
    @Provides
    fun getSignUpViewModelFactory(userRepository: UserRepository): SignUpViewModelFactory {
        return SignUpViewModelFactory(userRepository)
    }
}
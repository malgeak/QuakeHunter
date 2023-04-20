package com.malgeak.quakehunter.di.credentials.login

import com.malgeak.quakehunter.data.repository.user.UserRepository
import com.malgeak.quakehunter.ui.credentials.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @LoginScope
    @Provides
    fun getLoginViewModelFactory(userRepository: UserRepository): LoginViewModelFactory {
        return LoginViewModelFactory(userRepository)
    }
}
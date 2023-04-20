package com.malgeak.quakehunter.di.core

import android.content.Context
import com.malgeak.quakehunter.di.credentials.login.LoginSubComponent
import com.malgeak.quakehunter.di.credentials.signup.SignupSubComponent
import com.malgeak.quakehunter.di.quakehunter.home.HomeSubComponent
import com.malgeak.quakehunter.di.quakehunter.list.ListSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [LoginSubComponent::class, SignupSubComponent::class, HomeSubComponent::class, ListSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun getApplicationContext(): Context {
        return context
    }
}
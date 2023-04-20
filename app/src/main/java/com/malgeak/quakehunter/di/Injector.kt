package com.malgeak.quakehunter.di

import com.malgeak.quakehunter.di.credentials.login.LoginSubComponent
import com.malgeak.quakehunter.di.credentials.signup.SignupSubComponent
import com.malgeak.quakehunter.di.quakehunter.home.HomeSubComponent
import com.malgeak.quakehunter.di.quakehunter.list.ListSubComponent


interface Injector {
    fun createLoginSubComponent(): LoginSubComponent
    fun createSignUpSubComponent(): SignupSubComponent
    fun createHomeSubComponent(): HomeSubComponent
    fun createListSubCoponent(): ListSubComponent
}
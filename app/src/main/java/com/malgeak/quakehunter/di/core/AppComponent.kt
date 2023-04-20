package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.di.credentials.login.LoginSubComponent
import com.malgeak.quakehunter.di.credentials.signup.SignupSubComponent
import com.malgeak.quakehunter.di.quakehunter.home.HomeSubComponent
import com.malgeak.quakehunter.di.quakehunter.list.ListSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        CacheDataModule::class,
        SharedDataModule::class
    ]
)
interface AppComponent {

    fun getLoginSubComponent(): LoginSubComponent.Factory

    fun getSignupSubcomponent(): SignupSubComponent.Factory

    fun getHomeSubComponent(): HomeSubComponent.Factory

    fun getListSubComponent(): ListSubComponent.Factory
}


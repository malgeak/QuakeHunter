package com.malgeak.quakehunter

import android.app.Application
import com.malgeak.quakehunter.di.core.AppComponent
import com.malgeak.quakehunter.di.core.AppModule
import com.malgeak.quakehunter.di.core.NetworkModule
import com.malgeak.quakehunter.di.Injector
import com.malgeak.quakehunter.di.core.DaggerAppComponent
import com.malgeak.quakehunter.di.credentials.login.LoginSubComponent
import com.malgeak.quakehunter.di.credentials.signup.SignupSubComponent
import com.malgeak.quakehunter.di.quakehunter.home.HomeSubComponent
import com.malgeak.quakehunter.di.quakehunter.list.ListSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
       appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = applicationContext))
            .networkModule(NetworkModule(baseUrl = BuildConfig.BASE_URL)).build()

    }

    override fun createLoginSubComponent(): LoginSubComponent {
        return appComponent.getLoginSubComponent().create()
    }

    override fun createSignUpSubComponent(): SignupSubComponent {
        return appComponent.getSignupSubcomponent().create()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.getHomeSubComponent().create()
    }

    override fun createListSubCoponent(): ListSubComponent {
        return appComponent.getListSubComponent().create()
    }

}

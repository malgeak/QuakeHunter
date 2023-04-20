package com.malgeak.quakehunter.di.core

import android.content.Context
import com.malgeak.quakehunter.data.sharedpreferences.RequestAPIStatusPreferences
import com.malgeak.quakehunter.data.sharedpreferences.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedDataModule {

    @Singleton
    @Provides
    fun getUserPreferences(context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Singleton
    @Provides
    fun getEarthQuakeAPIPreferences(context: Context): RequestAPIStatusPreferences {
        return RequestAPIStatusPreferences(context)
    }
}
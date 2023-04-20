package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.data.datasource.cache.EarthQuakesCacheDataSource
import com.malgeak.quakehunter.data.datasource.cache.EarthQuakesCacheDataSourceImpl
import com.malgeak.quakehunter.data.datasource.cache.UserCacheDataSource
import com.malgeak.quakehunter.data.datasource.cache.UserCacheDataSourceImpl
import com.malgeak.quakehunter.data.sharedpreferences.RequestAPIStatusPreferences
import com.malgeak.quakehunter.data.sharedpreferences.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun getEarthQuakesCacheDataSource(requestAPIStatusPreferences: RequestAPIStatusPreferences): EarthQuakesCacheDataSource {
        return EarthQuakesCacheDataSourceImpl(requestAPIStatusPreferences)
    }

    @Singleton
    @Provides
    fun getUserCacheDataSource(userPreferences: UserPreferences): UserCacheDataSource {
        return UserCacheDataSourceImpl(userPreferences)
    }
}
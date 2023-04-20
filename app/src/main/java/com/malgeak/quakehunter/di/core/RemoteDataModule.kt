package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.data.api.EarthQuakeAPI
import com.malgeak.quakehunter.data.datasource.remote.EarthQuakesRemoteDataSource
import com.malgeak.quakehunter.data.datasource.remote.EarthQuakesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule() {

    @Singleton
    @Provides
    fun getEarthQuakeRemoteDataSource(earthQuakeAPI: EarthQuakeAPI): EarthQuakesRemoteDataSource {
        return EarthQuakesRemoteDataSourceImpl(earthQuakeAPI)
    }
}
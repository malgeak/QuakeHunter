package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.data.database.EarthQuakeDao
import com.malgeak.quakehunter.data.datasource.local.EarthQuakesLocalDataSource
import com.malgeak.quakehunter.data.datasource.local.EarthQuakesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun getEarthQuakeLocalDataSource(earthQuakeDao: EarthQuakeDao): EarthQuakesLocalDataSource {
        return EarthQuakesLocalDataSourceImpl(earthQuakeDao)
    }
}
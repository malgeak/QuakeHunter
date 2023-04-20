package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.data.datasource.cache.EarthQuakesCacheDataSource
import com.malgeak.quakehunter.data.datasource.cache.UserCacheDataSource
import com.malgeak.quakehunter.data.datasource.local.EarthQuakesLocalDataSource
import com.malgeak.quakehunter.data.datasource.remote.EarthQuakesRemoteDataSource
import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepository
import com.malgeak.quakehunter.data.repository.earthquake.EarthQuakeRepositoryImpl
import com.malgeak.quakehunter.data.repository.user.UserRepository
import com.malgeak.quakehunter.data.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getUserRepository(localCacheDataSource: UserCacheDataSource): UserRepository {
        return UserRepositoryImpl(localCacheDataSource)
    }

    @Singleton
    @Provides
    fun getEarthQuakeRepository(
        localDataSource: EarthQuakesLocalDataSource,
        cacheDataSource: EarthQuakesCacheDataSource,
        remoteDataSource: EarthQuakesRemoteDataSource
    ): EarthQuakeRepository {

        return EarthQuakeRepositoryImpl(localDataSource, cacheDataSource, remoteDataSource)
    }

}
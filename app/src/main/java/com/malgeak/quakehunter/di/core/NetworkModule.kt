package com.malgeak.quakehunter.di.core

import com.malgeak.quakehunter.data.api.EarthQuakeAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun getEarthQuakeAPI(retrofit: Retrofit): EarthQuakeAPI {
        return retrofit.create(EarthQuakeAPI::class.java)
    }
}
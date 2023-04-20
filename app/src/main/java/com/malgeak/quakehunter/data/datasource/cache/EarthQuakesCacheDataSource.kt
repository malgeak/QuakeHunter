package com.malgeak.quakehunter.data.datasource.cache

interface EarthQuakesCacheDataSource {

    suspend fun getLastDateRequested(): String?
    suspend fun getLastUpdateDate(): String?
    suspend fun saveLastDateRequested(date: String)
}
package com.malgeak.quakehunter.data.datasource.cache

import com.malgeak.quakehunter.data.sharedpreferences.RequestAPIStatusPreferences

class EarthQuakesCacheDataSourceImpl(private val requestPrefereces: RequestAPIStatusPreferences):
    EarthQuakesCacheDataSource {

    override suspend fun getLastDateRequested(): String? {
        return requestPrefereces.dateRequest
    }

    override suspend fun getLastUpdateDate(): String? {
        return requestPrefereces.dateUpdate
    }

    override suspend fun saveLastDateRequested(date: String) {
        requestPrefereces.dateRequest = date
    }
}
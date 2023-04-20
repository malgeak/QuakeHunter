package com.malgeak.quakehunter.data.repository.earthquake

import android.util.Log
import com.malgeak.quakehunter.data.datasource.cache.EarthQuakesCacheDataSource
import com.malgeak.quakehunter.data.datasource.local.EarthQuakesLocalDataSource
import com.malgeak.quakehunter.data.datasource.remote.EarthQuakesRemoteDataSource
import com.malgeak.quakehunter.data.model.EarthQuake

class EarthQuakeRepositoryImpl(val localDataSource: EarthQuakesLocalDataSource,
                               val cacheDataSource: EarthQuakesCacheDataSource,
                               val remoteDataSource: EarthQuakesRemoteDataSource):
    EarthQuakeRepository {
    private val TAG = this::class.java.canonicalName

    override suspend fun getLastConsult(): List<EarthQuake>? {

        var listQuakes: List<EarthQuake>? = null

        try {
            listQuakes = localDataSource.getEarthQuakesFromDB()
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }

        return listQuakes
    }

    override suspend fun getEarthquakes(selectDate: String, endDate: String): List<EarthQuake>? {

        var listQuakes: List<EarthQuake>? = null

        try {
            val response = remoteDataSource.downloadEarthQuakesByDate(selectDate, endDate)
            if (response.body() != null) {
                listQuakes = response.body()!!.earthQuakes
            }
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }

        clearDB()
        saveLastDateRequested(selectDate)
        saveListQuakes(listQuakes)

        return listQuakes
    }

    override suspend fun getLastDateConsult(): String? {

        var lastDate: String? = null
        try {
            lastDate = cacheDataSource.getLastDateRequested()
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }

        return lastDate
    }

    private suspend fun saveListQuakes(listQuakes: List<EarthQuake>?) {

        if (listQuakes == null || listQuakes.size <= 0) {
            return
        }

        try {
            localDataSource.saveEarthQuakestoDB(listQuakes)
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }
    }

    private suspend fun clearDB() {

        try {
            localDataSource.clearAll()
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }
    }

    private suspend fun saveLastDateRequested(date: String) {

        try {
            cacheDataSource.saveLastDateRequested(date)
        } catch (e: Exception) {
            Log.e(TAG,e.message.toString())
        }
    }
}
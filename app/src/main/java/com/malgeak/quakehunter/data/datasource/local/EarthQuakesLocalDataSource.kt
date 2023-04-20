package com.malgeak.quakehunter.data.datasource.local

import com.malgeak.quakehunter.data.model.EarthQuake

interface EarthQuakesLocalDataSource {

    suspend fun getEarthQuakesFromDB(): List<EarthQuake>
    suspend fun saveEarthQuakestoDB(earthquakes: List<EarthQuake>)
    suspend fun clearAll()
}
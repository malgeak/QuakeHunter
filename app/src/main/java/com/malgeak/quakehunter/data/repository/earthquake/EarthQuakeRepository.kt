package com.malgeak.quakehunter.data.repository.earthquake

import com.malgeak.quakehunter.data.model.EarthQuake

interface EarthQuakeRepository {

    suspend fun getLastConsult(): List<EarthQuake>?
    suspend fun getEarthquakes(selectDate: String, endDate: String): List<EarthQuake>?
    suspend fun getLastDateConsult(): String?
}
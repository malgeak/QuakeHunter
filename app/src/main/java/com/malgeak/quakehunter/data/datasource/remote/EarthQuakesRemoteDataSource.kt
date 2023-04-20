package com.malgeak.quakehunter.data.datasource.remote

import com.malgeak.quakehunter.data.model.ResponseApi
import retrofit2.Response
import java.util.Date

interface EarthQuakesRemoteDataSource {

    suspend fun downloadEarthQuakesByDate(dateSelected: String, dateEnd: String): Response<ResponseApi>
}
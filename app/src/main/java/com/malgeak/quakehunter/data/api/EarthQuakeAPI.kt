package com.malgeak.quakehunter.data.api

import com.malgeak.quakehunter.data.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthQuakeAPI {
    companion object{
        const val FORMAT_JSON = "geojson"
    }

    @GET("fdsnws/event/1/query")
    suspend fun getEarthQuakeList(
        @Query("format") format: String,
        @Query("starttime") startTime: String,
        @Query("endtime") endTime: String,
        @Query("limit") limit:Int = 20
    ): Response<ResponseApi>

}
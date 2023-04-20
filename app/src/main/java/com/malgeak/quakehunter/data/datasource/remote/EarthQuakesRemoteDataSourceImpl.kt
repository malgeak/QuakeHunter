package com.malgeak.quakehunter.data.datasource.remote

import com.malgeak.quakehunter.data.api.EarthQuakeAPI
import com.malgeak.quakehunter.data.model.ResponseApi
import retrofit2.Response
import java.util.*


class EarthQuakesRemoteDataSourceImpl(private val earthQuakeService: EarthQuakeAPI): EarthQuakesRemoteDataSource {

    override suspend fun downloadEarthQuakesByDate(dateSelected: String, dateEnd: String): Response<ResponseApi> {
        return earthQuakeService.getEarthQuakeList(EarthQuakeAPI.FORMAT_JSON, dateSelected, dateEnd)
    }
}
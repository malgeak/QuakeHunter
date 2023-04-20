package com.malgeak.quakehunter.data.model


import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("features")
    val earthQuakes: List<EarthQuake>
)
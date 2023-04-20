package com.malgeak.quakehunter.data.model


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("mag")
    val mag: Double,
    @SerializedName("place")
    val place: String?,
    @SerializedName("time")
    val time: Long,
    @SerializedName("updated")
    val updated: Long?,
    @SerializedName("magType")
    val magType: String?,
    @ColumnInfo(name = "type_feature")
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?
)
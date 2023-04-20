package com.malgeak.quakehunter.data.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "earthquake")
data class EarthQuake(
    @SerializedName("type")
    val type: String,
    @Embedded
    @SerializedName("properties")
    val properties: Properties,
    @Embedded
    @SerializedName("geometry")
    val geometry: Geometry,
    @PrimaryKey
    @SerializedName("id")
    val id: String
)
package com.malgeak.quakehunter.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun doubleListToString(list: List<Double>?): String? {
        if (list == null) {
            return null
        }
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToListDouble(list: String?): List<Double>? {
        if (list == null) {
            return null
        }
        return Gson().fromJson(list, List::class.java) as List<Double>
    }
}
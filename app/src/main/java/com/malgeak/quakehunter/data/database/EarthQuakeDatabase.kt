package com.malgeak.quakehunter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.malgeak.quakehunter.data.database.converter.Converters
import com.malgeak.quakehunter.data.model.EarthQuake

@Database(entities = [EarthQuake::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EarthQuakeDatabase: RoomDatabase() {

    abstract fun getEarthQuakeDao(): EarthQuakeDao
}
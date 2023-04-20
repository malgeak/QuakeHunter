package com.malgeak.quakehunter.di.core

import android.content.Context
import androidx.room.Room
import com.malgeak.quakehunter.data.database.EarthQuakeDao
import com.malgeak.quakehunter.data.database.EarthQuakeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getEarthquakesDatabase(context: Context): EarthQuakeDatabase {
        return Room.databaseBuilder(context, EarthQuakeDatabase::class.java, "earthdb").build()
    }

    @Singleton
    @Provides
    fun getEarthQuakesDao(earthQuakeDatabase: EarthQuakeDatabase): EarthQuakeDao {
        return earthQuakeDatabase.getEarthQuakeDao()
    }
}
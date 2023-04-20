package com.malgeak.quakehunter.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malgeak.quakehunter.data.model.EarthQuake

@Dao
interface EarthQuakeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEarthQuakes(earthQuakes: List<EarthQuake>)

    @Query("DELETE FROM earthquake")
    suspend fun deleteAllEarthquakes()

    @Query("SELECT * FROM earthquake")
    suspend fun getLastRequest(): List<EarthQuake>
}
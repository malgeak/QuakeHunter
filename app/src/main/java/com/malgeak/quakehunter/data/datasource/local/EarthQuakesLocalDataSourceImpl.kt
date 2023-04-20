package com.malgeak.quakehunter.data.datasource.local

import com.malgeak.quakehunter.data.database.EarthQuakeDao
import com.malgeak.quakehunter.data.model.EarthQuake
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EarthQuakesLocalDataSourceImpl(val earthQuakeDao: EarthQuakeDao): EarthQuakesLocalDataSource {

    override suspend fun getEarthQuakesFromDB(): List<EarthQuake> {
        return earthQuakeDao.getLastRequest()
    }

    override suspend fun saveEarthQuakestoDB(earthquakes: List<EarthQuake>) {
        CoroutineScope(Dispatchers.IO).launch {
            earthQuakeDao.saveEarthQuakes(earthquakes)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            earthQuakeDao.deleteAllEarthquakes()
        }
    }
}
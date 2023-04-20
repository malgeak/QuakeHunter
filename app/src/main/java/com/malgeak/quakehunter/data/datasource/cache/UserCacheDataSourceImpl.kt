package com.malgeak.quakehunter.data.datasource.cache

import com.malgeak.quakehunter.data.sharedpreferences.UserPreferences

class UserCacheDataSourceImpl(val userPreferences: UserPreferences): UserCacheDataSource {

    override suspend fun saveUser(username: String, password: String) {

        userPreferences.name = username
        userPreferences.password = password
    }

    override suspend fun checkUser(): Boolean {
        return userPreferences.name != null
    }

    override suspend fun validCredentials(username: String, password: String): Boolean{
        return (userPreferences.name == username && userPreferences.password == password)
    }
}
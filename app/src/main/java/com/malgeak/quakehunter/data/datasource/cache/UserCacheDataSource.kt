package com.malgeak.quakehunter.data.datasource.cache

interface UserCacheDataSource {

    suspend fun saveUser(username: String, password: String)
    suspend fun checkUser(): Boolean
    suspend fun validCredentials(username: String, password: String): Boolean
}
package com.malgeak.quakehunter.data.repository.user

import android.util.Log
import com.malgeak.quakehunter.data.datasource.cache.UserCacheDataSource

class UserRepositoryImpl(private val localUserCacheDataSource: UserCacheDataSource):
    UserRepository {
    private val TAG = this::class.java.canonicalName

    override suspend fun signup(name: String, password: String): Boolean {

        var success = false

        try {
            localUserCacheDataSource.saveUser(name,password)
            success = true
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

        return success
    }

    override suspend fun login(name: String, password: String): Boolean {

        var validCredentials = false

        try {
            validCredentials = localUserCacheDataSource.validCredentials(name,password)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

        return validCredentials
    }

    override suspend fun checkUser(): Boolean {
        return localUserCacheDataSource.checkUser()
    }
}
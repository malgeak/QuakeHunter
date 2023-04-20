package com.malgeak.quakehunter.data.repository.user

interface UserRepository {

    suspend fun signup(name: String, password: String): Boolean
    suspend fun login(name: String, password: String): Boolean
    suspend fun checkUser(): Boolean
}
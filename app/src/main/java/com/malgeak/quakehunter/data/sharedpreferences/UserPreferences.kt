package com.malgeak.quakehunter.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context:Context) {
    private val PREFERENCES_NAME = "com.malgeak.quakehunter.data.sharedpreferences.userpreferences"
    private val USER_NAME = "username"
    private val USER_PASSWORD_NAME = "password"

    val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var name: String?
        get() = preferences.getString(USER_NAME, null)
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    var password: String?
        get() = preferences.getString(USER_PASSWORD_NAME, null)
        set(value) = preferences.edit().putString(USER_PASSWORD_NAME, value).apply()
}

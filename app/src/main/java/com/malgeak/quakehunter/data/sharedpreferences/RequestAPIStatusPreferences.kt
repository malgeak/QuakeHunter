package com.malgeak.quakehunter.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class RequestAPIStatusPreferences(context: Context) {
    private val PREFERENCES_NAME = "com.malgeak.quakehunter.data.sharedpreferences.statusrequestpreferences"
    private val LAST_REQUEST_DATE_NAME = "date_request"
    private val LAST_UPDATE_DATE = "date_update"

    val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var dateRequest: String?
        get() = preferences.getString(LAST_REQUEST_DATE_NAME, null)
        set(value) = preferences.edit().putString(LAST_REQUEST_DATE_NAME, value).apply()

    var dateUpdate: String?
        get() = preferences.getString(LAST_UPDATE_DATE, null)
        set(value) = preferences.edit().putString(LAST_UPDATE_DATE, value).apply()
}
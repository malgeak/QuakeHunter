package com.malgeak.quakehunter.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.malgeak.quakehunter.ui.credentials.CredentialsActivity

class MainApplication : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splas = installSplashScreen()
        super.onCreate(savedInstanceState)
        splas.setKeepOnScreenCondition { true }

        SystemClock.sleep(3000)

        val intent = Intent(this, CredentialsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
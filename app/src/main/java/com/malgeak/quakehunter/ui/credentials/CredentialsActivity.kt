package com.malgeak.quakehunter.ui.credentials

import android.os.Bundle
import com.malgeak.quakehunter.R
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController

import com.malgeak.quakehunter.databinding.ActivityCredentialsBinding


class CredentialsActivity : FragmentActivity() {

    private lateinit var binding: ActivityCredentialsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCredentialsBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if (binding.navHostFragment.findNavController().currentDestination?.id == R.id.loginFragment) {
            finishAndRemoveTask()
        }

    }
}
package com.malgeak.quakehunter.ui.quakehunter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.malgeak.quakehunter.R
import com.malgeak.quakehunter.databinding.ActivityMainBinding
import com.malgeak.quakehunter.ui.credentials.CredentialsActivity

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_QuakeHunter)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater, null, false)

        setContentView(binding.root)
    }

    fun setLoader(boolean: Boolean) {
        if(boolean) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    fun setTitleToolBar(title: String) {
        binding.toolbar.title = title
    }

    override fun onBackPressed() {
        if (binding.navHostFragment.findNavController().currentDestination?.id == R.id.homeFragment) {
            val builder = AlertDialog.Builder(this)

            builder.setMessage(getString(R.string.msg_logout))
                .setPositiveButton( R.string.msg_acept,
                    DialogInterface.OnClickListener{ dialogInterface, i ->
                        val intent = Intent(this, CredentialsActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                .setNegativeButton(getString(R.string.msg_cancel),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                ).create()

            builder.show()
        } else {
            binding.navHostFragment.findNavController().popBackStack()
        }
    }
}
package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service.service_route

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityServiceRouteBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.ar_map.ArMapActivity

class ServiceRouteActivity : AppCompatActivity() {
    lateinit var binding: ActivityServiceRouteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonStart1.setOnClickListener {
            startActivity(Intent(this, ArMapActivity::class.java))
        }
        binding.buttonStart2.setOnClickListener {
            startActivity(Intent(this, ArMapActivity::class.java))
        }
        binding.buttonStart3.setOnClickListener {
            startActivity(Intent(this, ArMapActivity::class.java))
        }

        binding.buttonComleted.setOnClickListener {
            Toast.makeText(this, "Your information is sent to the admin!", Toast.LENGTH_LONG).show()
        }

    }

    // Handle when pressing back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
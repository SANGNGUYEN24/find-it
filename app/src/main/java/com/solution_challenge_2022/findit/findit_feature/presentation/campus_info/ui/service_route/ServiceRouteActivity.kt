package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.service_route

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityServiceRouteBinding

class ServiceRouteActivity : AppCompatActivity() {
    lateinit var binding: ActivityServiceRouteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
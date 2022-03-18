package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.solution_challenge_2022.findit.databinding.ActivityFullMapBinding

class FullMapActivity : AppCompatActivity() {
    lateinit var binding : ActivityFullMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
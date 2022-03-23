package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityDestinationSearchBinding

class DestinationSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityDestinationSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
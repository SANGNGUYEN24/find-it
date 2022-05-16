package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityDestinationSearchBinding

class DestinationSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityDestinationSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(
            com.google.android.material.R.anim.abc_fade_in,
            com.google.android.material.R.anim.abc_fade_out
        )

        binding = ActivityDestinationSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
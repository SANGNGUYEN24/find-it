package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.full_map

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityFullMapBinding
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_FULL_MAP_LINK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullMapActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullMapBinding
    private val fullMapViewModel: FullMapViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullMapViewModel.updateMapLink(
            intent.getStringExtra(CAMPUS_INFO_TO_FULL_MAP_LINK).toString()
        )
    }
}
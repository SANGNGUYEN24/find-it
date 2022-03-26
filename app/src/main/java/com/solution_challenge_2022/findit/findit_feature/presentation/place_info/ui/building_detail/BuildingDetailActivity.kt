package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.building_detail

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityBuildingDetailBinding
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_NAME
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BuildingDetailActivity : AppCompatActivity() {
    private lateinit var campusId: String
    private lateinit var buildingId: String
    private lateinit var currentBuildingId: String // Building Id when user scan QR code
    private lateinit var currentBuildingName: String
    lateinit var binding: ActivityBuildingDetailBinding
    private val buildingDetailViewModel: BuildingDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        campusId = intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID).toString()
        buildingId = intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID).toString()
        currentBuildingId =
            intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_ID).toString()
        currentBuildingName =
            intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_NAME).toString()

        buildingDetailViewModel.updateBuildingDetailData(
            campusId,
            buildingId,
            currentBuildingId,
            currentBuildingName
        )
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
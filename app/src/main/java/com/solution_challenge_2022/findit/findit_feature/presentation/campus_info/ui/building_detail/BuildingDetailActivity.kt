package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.building_detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.databinding.ActivityBuildingDetailBinding
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingDetailActivity : AppCompatActivity() {
    private lateinit var campusId: String
    private lateinit var buildingId: String
    lateinit var binding: ActivityBuildingDetailBinding
    private val buildingDetailViewModel: BuildingDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        campusId = intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID).toString()
        buildingId = intent.getStringExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID).toString()
        Log.d("Find It BuildingDetailActivity", "$campusId, $buildingId")
        buildingDetailViewModel.getBuildingDetail(campusId, buildingId)
//        var i = 0;
//        while (i < 5) {
//            Toast.makeText(this, "${buildingDetailViewModel.building.value}", Toast.LENGTH_SHORT)
//                .show()
//            i++;
//        }
    }
}
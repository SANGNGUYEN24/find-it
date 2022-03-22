package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.building_detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.databinding.FragmentBuildingDetailBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.ar_map.ArMapActivity
import com.solution_challenge_2022.findit.util.Constant.Companion.BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID

class BuildingDetailFragment : Fragment() {
    lateinit var binding: FragmentBuildingDetailBinding
    private val buildingDetailViewModel: BuildingDetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuildingDetailBinding.inflate(inflater)
        binding.buildingDetailViewModel = buildingDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabStartArMap.setOnClickListener {
            val currentBuildingId = buildingDetailViewModel.currentBuildingId.value
            val destinationBuildingId = buildingDetailViewModel.building.value?.buildingId
            Toast.makeText(
                context,
                "$currentBuildingId-$destinationBuildingId ",
                Toast.LENGTH_SHORT
            ).show()

            val goToArMap = Intent(context, ArMapActivity::class.java)
            goToArMap.putExtra(BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID, currentBuildingId)
            goToArMap.putExtra(
                BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID,
                destinationBuildingId
            )
            startActivity(goToArMap)
        }
    }
}
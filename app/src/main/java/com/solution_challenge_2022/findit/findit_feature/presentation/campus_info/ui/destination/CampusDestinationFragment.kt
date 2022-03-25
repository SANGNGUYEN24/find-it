package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.databinding.FragmentCampusDestinationBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.building_detail.BuildingDetailActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.full_map.FullMapActivity
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_ID
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_NAME
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS_INFO_TO_FULL_MAP_LINK

class CampusDestinationFragment : Fragment() {
    private lateinit var binding: FragmentCampusDestinationBinding

    // Get ViewModel from parent Activity
    private val campusViewModel: CampusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCampusDestinationBinding.inflate(inflater)
        // Set the viewModel for data binding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.campusViewModel = campusViewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardCampusMap.setOnClickListener {
            val goToFullMap = Intent(activity, FullMapActivity::class.java)
            goToFullMap.putExtra(
                CAMPUS_INFO_TO_FULL_MAP_LINK,
                campusViewModel.campusInfo.value?.mapLink
            )
            startActivity(goToFullMap)
        }

        binding.recyclerViewPopularAreas.adapter =
            PopularAreasAdapter(PopularAreaListener { campusId, buildingId ->
                val goToBuildingDetail = Intent(context, BuildingDetailActivity::class.java)
                goToBuildingDetail.putExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID, campusId)
                goToBuildingDetail.putExtra(CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID, buildingId)
                val currentBuildingId = campusViewModel.currentBuilding.value?.buildingId
                val currentBuildingName = campusViewModel.currentBuilding.value?.buildingName
                goToBuildingDetail.putExtra(
                    CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_ID,
                    currentBuildingId
                )
                goToBuildingDetail.putExtra(
                    CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_NAME,
                    currentBuildingName
                )
                Log.d("Find It CampusDestinationFragment", "$campusId, $buildingId")
                startActivity(goToBuildingDetail)
            })

        campusViewModel.srcToGetData.observe(viewLifecycleOwner) { srcToGetData ->
            if (srcToGetData == "from_home") {
                binding.currentBuilding.visibility = View.GONE
                binding.youAreHere.visibility = View.GONE
            }
        }
    }
}
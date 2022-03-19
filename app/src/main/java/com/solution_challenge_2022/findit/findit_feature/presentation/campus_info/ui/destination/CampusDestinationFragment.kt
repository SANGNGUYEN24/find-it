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

        binding.recyclerViewPopularAreas.adapter = PopularAreasAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        campusViewModel.campusInfo.observe(viewLifecycleOwner) { campusInfo ->
            Log.d("CampusDestinationFragment", campusInfo?.mapLink.toString())
        }
        campusViewModel.popularAreasList.observe(viewLifecycleOwner) { x ->
            Log.d("CampusDestinationFragment", x.toString())
        }

        binding.destinationSearch.setOnClickListener {
            val gotoDestinationSearch = Intent(activity, DestinationSearchActivity::class.java)
            startActivity(gotoDestinationSearch)
        }

        binding.cardCampusMap.setOnClickListener {
            val goToFullMap = Intent(activity, FullMapActivity::class.java)
            startActivity(goToFullMap)
        }

        binding.currentBuilding.setOnClickListener {
            val gotoBuildingDetail = Intent(activity, BuildingDetailActivity::class.java)
            startActivity(gotoBuildingDetail)
        }

    }
}
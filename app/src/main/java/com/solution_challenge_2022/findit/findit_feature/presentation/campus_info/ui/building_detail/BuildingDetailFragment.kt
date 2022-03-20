package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.building_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.databinding.FragmentBuildingDetailBinding

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

}
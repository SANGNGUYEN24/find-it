package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination.full_map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.databinding.FragmentFullMapBinding

class FullMapFragment : Fragment() {
    lateinit var binding: FragmentFullMapBinding
    private val fullMapViewModel: FullMapViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullMapBinding.inflate(inflater)
        // Set the viewModel for data binding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.fullMapViewModel = fullMapViewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = this

        return binding.root
    }
}
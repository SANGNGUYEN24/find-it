package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.FragmentCampusInfoBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.CampusViewModel

class CampusInfoFragment : Fragment() {
    private lateinit var binding: FragmentCampusInfoBinding

    // Get ViewModel from parent Activity
    private val campusViewModel: CampusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_campus_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the viewModel for data binding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.campusViewModel = campusViewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
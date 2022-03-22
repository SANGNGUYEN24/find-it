package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.FragmentCampusServiceBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewModel

class CampusServiceFragment : Fragment() {
    private lateinit var binding: FragmentCampusServiceBinding

    // Get ViewModel from parent Activity
    private val campusViewModel: CampusViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_campus_service, container, false)
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

        binding.recyclerViewService.adapter = ServiceAdapter(
            ServiceListener { campusId, serviceId ->
                Toast.makeText(context, "$campusId-$serviceId", Toast.LENGTH_SHORT).show()

            }
        )
    }
}
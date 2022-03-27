package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.FragmentPlaceServiceBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.PlaceViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service.service_route.ServiceRouteActivity

class PlaceServiceFragment : Fragment() {
    private lateinit var binding: FragmentPlaceServiceBinding

    // Get ViewModel from parent Activity
    private val placeViewModel: PlaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout XML file and return a binding object instance
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_place_service, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the viewModel for data binding - this allows the bound layout access
        // to all the data in the VieWModel
        binding.campusViewModel = placeViewModel
        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerViewService.adapter = ServiceAdapter(
            ServiceListener { campusId, serviceId ->
                val goToServiceRouteActivity = Intent(context, ServiceRouteActivity::class.java)
                startActivity(goToServiceRouteActivity)
            }
        )
    }
}
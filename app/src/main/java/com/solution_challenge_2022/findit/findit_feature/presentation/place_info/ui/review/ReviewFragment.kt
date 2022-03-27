package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.solution_challenge_2022.findit.databinding.FragmentReviewBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.PlaceViewModel

class ReviewFragment : Fragment() {
    private val placeViewModel: PlaceViewModel by activityViewModels()
    lateinit var binding: FragmentReviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.placeViewModel = placeViewModel
        binding.lifecycleOwner = this

        binding.recyclerViewReview.adapter = ReviewAdapter()

    }
}
package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.solution_challenge_2022.findit.databinding.FragmentReviewBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.PlaceViewModel
import com.solution_challenge_2022.findit.util.Constant.Companion.REVIEW_FRAGMENT_TO_REVIEW_UPLOAD_RATING

class ReviewFragment : Fragment(), RatingBar.OnRatingBarChangeListener {
    var rating: Float = 0.0f
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

        binding.ratingBar.onRatingBarChangeListener = this

    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            rating = p1
            val goToReviewUpload = Intent(requireContext(), ReviewUploadActivity::class.java)
            goToReviewUpload.putExtra(REVIEW_FRAGMENT_TO_REVIEW_UPLOAD_RATING, rating)
            startActivity(goToReviewUpload)
        } else {
            Toast.makeText(requireContext(), "You must be logged in to vote!", Toast.LENGTH_SHORT)
                .show()
        }


    }
}
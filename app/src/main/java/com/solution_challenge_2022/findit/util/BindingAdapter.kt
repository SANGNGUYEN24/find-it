package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui

import android.net.Uri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.makeramen.roundedimageview.RoundedImageView
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.Review
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination.PopularAreasAdapter
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review.ReviewAdapter
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service.ServiceAdapter

@BindingAdapter("imageUrl")
fun bindProfileImage(image: RoundedImageView, imageUrl: Uri?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}

// Binding adapter for Campus Map using Glide
@BindingAdapter("imageUrl")
fun bindImage(image: RoundedImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}

@BindingAdapter("imageUrl")
fun bindFullImage(image: PhotoView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}

@BindingAdapter("listData")
fun bindPopularAreasRecyclerView(recyclerView: RecyclerView, data: List<Building>?) {
    val adapter = recyclerView.adapter as PopularAreasAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindServiceRecyclerView(recyclerView: RecyclerView, data: List<Service>?) {
    val adapter = recyclerView.adapter as ServiceAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindReviewRecyclerView(recyclerView: RecyclerView, data: List<Review>?) {
    val adapter = recyclerView.adapter as ReviewAdapter
    adapter.submitList(data)
}
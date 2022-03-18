package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.solution_challenge_2022.findit.R

// Binding adapter for Campus Map using Glide
@BindingAdapter("imageUrl")
fun bindCampusMapImage(image: RoundedImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}
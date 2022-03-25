package com.solution_challenge_2022.findit.findit_feature.presentation

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.solution_challenge_2022.findit.R

@BindingAdapter("imageUrl")
fun bindProfileImage(image: RoundedImageView, imageUrl: Uri?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}
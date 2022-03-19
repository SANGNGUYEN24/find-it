package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination.PopularAreasAdapter

// Binding adapter for Campus Map using Glide
@BindingAdapter("imageUrl")
fun bindImage(image: RoundedImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(image.context).load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(image)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Building>?) {
    val adapter = recyclerView.adapter as PopularAreasAdapter
    adapter.submitList(data)
}
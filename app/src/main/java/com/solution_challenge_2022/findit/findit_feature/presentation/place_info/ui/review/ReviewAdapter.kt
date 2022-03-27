package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solution_challenge_2022.findit.databinding.ReviewItemBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.Review

class ReviewAdapter : ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(ReviewDiffCallback) {

    inner class ReviewViewHolder(
        private val binding: ReviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.review = review
            binding.executePendingBindings()
        }
    }

    companion object ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.comment == newItem.comment
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ReviewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)!!
        holder.bind(review)
    }
}
package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solution_challenge_2022.findit.databinding.SuggestedPlaceItemBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

class SuggestedPlaceAdapter(private val clickListener: SuggestedPlaceListener) :
    ListAdapter<Building, SuggestedPlaceAdapter.SuggestedPlaceViewHolder>(
        SuggestedPlaceCallback
    ) {
    inner class SuggestedPlaceViewHolder(
        private val binding: SuggestedPlaceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building, clickListener: SuggestedPlaceListener) {
            binding.building = building
            binding.clickListener = clickListener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object SuggestedPlaceCallback : DiffUtil.ItemCallback<Building>() {
        override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem.buildingId == newItem.buildingId
        }

        override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedPlaceViewHolder {
        return SuggestedPlaceViewHolder(
            SuggestedPlaceItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: SuggestedPlaceViewHolder, position: Int) {
        val building = getItem(position)!!
        holder.bind(building, clickListener)
    }
}

class SuggestedPlaceListener(val clickListener: (campusId: String?, buildingId: String?) -> Unit) {
    fun onClick(building: Building) = clickListener(building.campusId, building.buildingId)
}
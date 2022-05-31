package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solution_challenge_2022.findit.databinding.EntertainmentVenueItemBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

class EntertainmentVenueAdapter(private val clickListener: EntertainmentVenueListener) :
    ListAdapter<Building, EntertainmentVenueAdapter.EntertainmentVenueViewHolder>(
        EntertainmentVenueCallback
    ) {
    inner class EntertainmentVenueViewHolder(
        private val binding: EntertainmentVenueItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building, clickListener: EntertainmentVenueListener) {
            binding.building = building
            binding.clickListener = clickListener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object EntertainmentVenueCallback : DiffUtil.ItemCallback<Building>() {
        override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem.buildingId == newItem.buildingId
        }

        override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntertainmentVenueViewHolder {
        return EntertainmentVenueViewHolder(
            EntertainmentVenueItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: EntertainmentVenueViewHolder, position: Int) {
        val building = getItem(position)!!
        holder.bind(building, clickListener)
    }
}

class EntertainmentVenueListener(val clickListener: (campusId: String?, buildingId: String?) -> Unit) {
    fun onClick(building: Building) = clickListener(building.campusId, building.buildingId)
}
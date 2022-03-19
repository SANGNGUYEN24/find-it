package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solution_challenge_2022.findit.databinding.PopularAreaItemBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

class PopularAreasAdapter :
    ListAdapter<Building, PopularAreasAdapter.PopularAreaViewHolder>(DiffCallback) {

    /**
     * The BuildingsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Building] information.
     */
    class PopularAreaViewHolder(
        private var binding: PopularAreaItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building) {
            binding.building = building
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Building] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Building>() {
        override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem.buildingName == newItem.buildingName
        }

        override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
            return oldItem.imageLink == newItem.imageLink
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAreaViewHolder {
        return PopularAreaViewHolder(
            PopularAreaItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PopularAreaViewHolder, position: Int) {
        val building = getItem(position)
        holder.bind(building)
    }
}
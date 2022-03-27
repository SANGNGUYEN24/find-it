package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solution_challenge_2022.findit.databinding.ServiceItemBinding
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service

class ServiceAdapter(private val clickListener: ServiceListener) :
    ListAdapter<Service, ServiceAdapter.ServiceViewHolder>(ServiceDiffCallback) {

    /**
     * The BuildingsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Service] information.
     */
    inner class ServiceViewHolder(
        private val binding: ServiceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Service, clickListener: ServiceListener) {
            binding.service = service
            binding.clickListener = clickListener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Service] has been updated.
     */
    companion object ServiceDiffCallback : DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.serviceId == newItem.serviceId
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.imageLink == newItem.imageLink
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {

        return ServiceViewHolder(
            ServiceItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = getItem(position)!!
        holder.bind(service, clickListener)
    }
}

class ServiceListener(val clickListener: (campusId: String?, serviceId: String?) -> Unit) {
    fun onClick(service: Service) = clickListener(service.campusId, service.serviceId)
}



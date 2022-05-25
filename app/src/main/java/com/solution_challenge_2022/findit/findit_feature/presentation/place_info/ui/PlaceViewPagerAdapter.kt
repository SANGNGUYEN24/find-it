package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.destination.PlaceDestinationFragment
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review.ReviewFragment
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.service.PlaceServiceFragment

class PlaceViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlaceDestinationFragment()
            1 -> PlaceServiceFragment()
            2 -> ReviewFragment()
            else -> PlaceDestinationFragment()
        }
    }
}
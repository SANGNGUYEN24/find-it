package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.destination.CampusDestinationFragment
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.event.CampusServiceFragment

class CampusViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CampusDestinationFragment()
            1 -> CampusServiceFragment()
            else -> CampusDestinationFragment()
        }
    }
}
package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.solution_challenge_2022.findit.databinding.ActivityCampusBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.test.ui.main.ViewPagerAdapter
import com.solution_challenge_2022.findit.util.Constant.Companion.TAB_TITLES

class CampusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCampusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCampusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pager = binding.viewPager
        val tabLayout = binding.tabs

        pager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
    }
}
package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.solution_challenge_2022.findit.databinding.ActivityCampusInfoBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.MainActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewPagerAdapter
import com.solution_challenge_2022.findit.util.Constant.Companion.QR_CODE_KEY
import com.solution_challenge_2022.findit.util.Constant.Companion.TAB_TITLES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampusInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityCampusInfoBinding
    lateinit var qrCodeOutput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val campusViewModel: CampusViewModel by viewModels()
        binding = ActivityCampusInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(
            com.google.android.material.R.anim.abc_slide_in_bottom,
            com.google.android.material.R.anim.abc_fade_out
        )

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Handle view pager
        val pager = binding.viewPager
        val tabLayout = binding.tabs
        pager.adapter = CampusViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

        /**
         * Receive QR code output from [MainActivity]
         * */
        qrCodeOutput = intent.getStringExtra(QR_CODE_KEY).toString()
        campusViewModel.updateQrCodeData(qrCodeOutput)
    }
}


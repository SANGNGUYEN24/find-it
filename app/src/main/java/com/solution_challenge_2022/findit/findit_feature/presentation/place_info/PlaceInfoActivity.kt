package com.solution_challenge_2022.findit.findit_feature.presentation.place_info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.solution_challenge_2022.findit.databinding.ActivityPlaceInfoBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.MainActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.PlaceViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.PlaceViewPagerAdapter
import com.solution_challenge_2022.findit.util.Constant.Companion.QR_CODE_KEY
import com.solution_challenge_2022.findit.util.Constant.Companion.SRC_TO_GET_PLACE_DETAIL
import com.solution_challenge_2022.findit.util.Constant.Companion.TAB_TITLES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlaceInfoBinding
    lateinit var qrCodeOutput: String
    lateinit var srcToGetData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(
            com.google.android.material.R.anim.abc_fade_in,
            com.google.android.material.R.anim.abc_fade_out
        )

        val placeViewModel: PlaceViewModel by viewModels()
        binding = ActivityPlaceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Handle view pager
        val pager = binding.viewPager
        val tabLayout = binding.tabs
        // Disable swipe because it cause laggy
        pager.isUserInputEnabled = false
        pager.adapter = PlaceViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

        /**
         * Receive QR code output from [MainActivity]
         * */
        qrCodeOutput = intent.getStringExtra(QR_CODE_KEY).toString()
        srcToGetData = intent.getStringExtra(SRC_TO_GET_PLACE_DETAIL).toString()

        placeViewModel.updateQrCodeData(qrCodeOutput)
        placeViewModel.updateSrcToGetData(srcToGetData)
    }
}


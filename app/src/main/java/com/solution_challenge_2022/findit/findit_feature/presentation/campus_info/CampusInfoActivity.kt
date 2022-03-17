package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.solution_challenge_2022.findit.databinding.ActivityCampusInfoBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.MainActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewPagerAdapter
import com.solution_challenge_2022.findit.util.Constant
import com.solution_challenge_2022.findit.util.Constant.Companion.QR_CODE_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampusInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityCampusInfoBinding
    private val TAG = "CampusInfoActivity"
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
            startActivity(Intent(this@CampusInfoActivity, MainActivity::class.java))
            finish()
        }

        // Handle view pager
        val pager = binding.viewPager
        val tabLayout = binding.tabs
        pager.adapter = CampusViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getString(Constant.TAB_TITLES[position])
        }.attach()

        /**
         * Receive QR code output from [MainActivity]
         * */
        qrCodeOutput = intent.getStringExtra(QR_CODE_KEY).toString()
        campusViewModel.updateQrCodeData(qrCodeOutput)
        Toast.makeText(this, qrCodeOutput, Toast.LENGTH_SHORT).show()
    }
}


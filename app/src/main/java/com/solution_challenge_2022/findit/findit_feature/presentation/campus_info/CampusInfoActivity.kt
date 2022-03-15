package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.solution_challenge_2022.findit.databinding.ActivityCampusInfoBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.MainActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewModel
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.CampusViewPagerAdapter
import com.solution_challenge_2022.findit.util.Constant
import com.solution_challenge_2022.findit.util.Constant.Companion.QR_CODE_KEY


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
        Toast.makeText(this, qrCodeOutput, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        checkPermission(android.Manifest.permission.CAMERA, Constant.CAMERA_PERMISSION_CODE)
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@CampusInfoActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@CampusInfoActivity,
                arrayOf(permission),
                requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constant.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPermission(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    Constant.READ_STORAGE_PERMISSION_CODE
                )
            } else {
                Toast.makeText(
                    this@CampusInfoActivity,
                    "Camera Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (requestCode == Constant.READ_STORAGE_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                checkPermission(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Constant.WRITE_STORAGE_PERMISSION_CODE
                )
            } else {
                Toast.makeText(
                    this@CampusInfoActivity,
                    "Storage Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (requestCode == Constant.WRITE_STORAGE_PERMISSION_CODE) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(
                    this@CampusInfoActivity,
                    "Storage Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
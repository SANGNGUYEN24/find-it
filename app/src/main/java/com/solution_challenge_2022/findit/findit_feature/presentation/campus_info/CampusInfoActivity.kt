package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.ActivityQrScannerBinding

class CampusInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityQrScannerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvQrResult = binding.tvQrResult
        val qrCodeData = intent.getStringExtra("qrResultCode");
        tvQrResult.text = qrCodeData

    }
}
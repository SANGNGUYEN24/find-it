package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class CampusViewModel : ViewModel() {
    lateinit var barcodeScanner: BarcodeScanner
    var _qrCodeData = MutableLiveData("Scan QR code to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData
}
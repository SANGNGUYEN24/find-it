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

    private var _qrCodeData = MutableLiveData("Scan QR code\n to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData

    fun readQr(inputImage: InputImage) {
        barcodeScanner = BarcodeScanning.getClient()
        barcodeScanner.process(inputImage).addOnSuccessListener {
            // handle success list
            for (barcode: Barcode in it) {
                var qrOutput = ""
                when (barcode.valueType) {
                    Barcode.TYPE_WIFI -> {
                        val ssid = barcode.wifi!!.ssid
                        val password = barcode.wifi!!.password
                        val type = barcode.wifi!!.encryptionType
                        qrOutput = "ssid: \n${ssid} password: \n${password} type: \n${type}"
                    }
                    Barcode.TYPE_URL -> {
                        val title = barcode.url!!.title
                        val url = barcode.url!!.url
                        qrOutput = "title: \n${title} url: \n${url}"
                    }
                    Barcode.TYPE_TEXT -> {
                        val data = barcode.displayValue
                        qrOutput = "data: \n${data}"
                    }
                }
                _qrCodeData.value = qrOutput
                Log.d("CampusViewModel", qrCodeData.value.toString())
                Log.d("CampusViewModel", _qrCodeData.value.toString())
            }
        }.addOnFailureListener {
            Log.d("CampusViewModel", "processQr: ${it.message}")
        }
    }
}
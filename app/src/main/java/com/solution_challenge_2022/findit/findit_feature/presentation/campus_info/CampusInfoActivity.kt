package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.ActivityQrScannerBinding

class CampusInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityQrScannerBinding
    lateinit var tvQrResult: TextView
//    lateinit var inputImage: InputImage
//    lateinit var barcodeScanner: BarcodeScanner
//    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
//    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private val TAG = "MyTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)

        binding = ActivityQrScannerBinding.inflate(layoutInflater)

        tvQrResult = binding.tvQrResult

//        barcodeScanner = BarcodeScanning.getClient()
//        cameraLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            val data = result?.data
//            try {
//                val photo = data?.extras?.get("data") as Bitmap
//                inputImage = InputImage.fromBitmap(photo, 0)
//                processQr()
//
//            } catch (e: Exception) {
//                Log.d(TAG, "onActivityResult: " + e.message)
//            }
//        }
//
//        galleryLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            val data = result?.data
//            inputImage =
//                data?.data?.let { InputImage.fromFilePath(this@QrScannerActivity, it) }!!
//            processQr()
//        }
//
//        // Show alert dialog
//        val options = arrayOf("Use camera", "Photo from gallery")
//        val builder = AlertDialog.Builder(this@QrScannerActivity)
//        builder.setTitle("Pick a option")
//
//        builder.setItems(options) { _, which ->
//            if (which == 0) {
//                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                cameraLauncher.launch(cameraIntent)
//            } else {
//                val storageIntent = Intent()
//                storageIntent.type = "image/*"
//                storageIntent.action = Intent.ACTION_GET_CONTENT
//                galleryLauncher.launch(storageIntent)
//            }
//        }
//        builder.show()

    }
//
//    private fun processQr() {
//        Log.d(TAG, "Process QR")
//        tvQrResult.visibility = VISIBLE
//
//        barcodeScanner.process(inputImage).addOnSuccessListener {
//            // handle success list
//            for (barcode: Barcode in it) {
//                print("QR code result: $barcode")
//
//                when (barcode.valueType) {
//                    Barcode.TYPE_WIFI -> {
//                        val ssid = barcode.wifi!!.ssid
//                        val password = barcode.wifi!!.password
//                        val type = barcode.wifi!!.encryptionType
//                        tvQrResult.text = "ssid: \n${ssid} password: \n${password} type: \n${type}"
//                    }
//                    Barcode.TYPE_URL -> {
//                        val title = barcode.url!!.title
//                        val url = barcode.url!!.url
//                        tvQrResult.text = "title: \n${title} url: \n${url}"
//                    }
//                    Barcode.TYPE_TEXT -> {
//                        val data = barcode.displayValue
//                        tvQrResult.text = "data: \n${data}"
//                    }
//                }
//
//            }
//        }.addOnFailureListener {
//            Log.d(TAG, "processQr: ${it.message}")
//        }
//    }


}
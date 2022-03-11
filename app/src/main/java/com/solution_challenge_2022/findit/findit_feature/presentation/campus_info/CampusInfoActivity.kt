package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.vision.common.InputImage
import com.solution_challenge_2022.findit.databinding.ActivityQrScannerBinding
import com.solution_challenge_2022.findit.util.Constant

class CampusInfoActivity: AppCompatActivity() {
    lateinit var binding: ActivityQrScannerBinding
    private lateinit var inputImage: InputImage
    private lateinit var campusViewModel: CampusViewModel
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private val TAG = "CampusInfoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        campusViewModel = ViewModelProvider(this)[CampusViewModel::class.java]
        binding = ActivityQrScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf("Use camera", "Photo from gallery")
        val builder = AlertDialog.Builder(this@CampusInfoActivity)
        builder.setTitle("Pick an option")
            .setItems(options) { _, which ->
                if (which == 0) {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    cameraLauncher.launch(cameraIntent)
                } else {
                    val storageIntent = Intent()
                    storageIntent.type = "image/*"
                    storageIntent.action = Intent.ACTION_GET_CONTENT
                    galleryLauncher.launch(storageIntent)
                }
            }.show()

        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result?.data
            try {
                val photo = data?.extras?.get("data") as Bitmap
                inputImage = InputImage.fromBitmap(photo, 0)
                campusViewModel.readQr(inputImage)

            } catch (e: Exception) {
                Log.d(TAG, "onActivityResult: " + e.message)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result?.data
            inputImage =
                data?.data?.let { InputImage.fromFilePath(this@CampusInfoActivity, it) }!!
            campusViewModel.readQr(inputImage)
        }
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
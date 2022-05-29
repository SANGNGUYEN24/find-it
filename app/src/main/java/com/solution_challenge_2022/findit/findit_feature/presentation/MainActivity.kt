package com.solution_challenge_2022.findit.findit_feature.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.ActivityMainBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.place_info.PlaceInfoActivity
import com.solution_challenge_2022.findit.util.Constant
import com.solution_challenge_2022.findit.util.Constant.Companion.QR_CODE_KEY
import com.solution_challenge_2022.findit.util.Constant.Companion.SRC_TO_GET_PLACE_DETAIL
import com.solution_challenge_2022.findit.util.DataMethod
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var inputImage: InputImage
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var barcodeScanner: BarcodeScanner
    lateinit var qrCodeOutput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(
            com.google.android.material.R.anim.abc_fade_in,
            com.google.android.material.R.anim.abc_fade_out
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        val bottomNavView = binding.bottomNavView
        val floatingActionButton = binding.floatingActionButton
        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)

        bottomNavView.background = null
        bottomNavView.menu.getItem(1).isEnabled = false // QR code scanner
        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(bottomNavView, navController)

        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result?.data
            try {
                val photo = data?.extras?.get("data") as Bitmap
                inputImage = InputImage.fromBitmap(photo, 0)
                readQr(inputImage)
            } catch (e: Exception) {
                Log.d("MainActivity", "onActivityResult: " + e.message)
            }
        }

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result?.data
            inputImage =
                data?.data?.let { InputImage.fromFilePath(this@MainActivity, it) }!!
            readQr(inputImage)
        }

        // Handle alert dialog
        val options = arrayOf("Use camera", "Photo from gallery")
        val dialog = MaterialAlertDialogBuilder(this@MainActivity, R.style.AlertDialogCustom)
        dialog.setTitle("Pick an option")
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
            }.create()

        // Show alert dialog
        floatingActionButton.setOnClickListener {
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermission(android.Manifest.permission.CAMERA, Constant.CAMERA_PERMISSION_CODE)
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
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
                    this@MainActivity,
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
                    this@MainActivity,
                    "Storage Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (requestCode == Constant.WRITE_STORAGE_PERMISSION_CODE) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(
                    this@MainActivity,
                    "Storage Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun readQr(inputImage: InputImage) {
        Toast.makeText(this, "Wait a second", Toast.LENGTH_LONG)
            .show()
        barcodeScanner = BarcodeScanning.getClient()
        Log.d("FindIt barcodeScanner", barcodeScanner.toString())
        barcodeScanner.process(inputImage).addOnSuccessListener {
            // handle success list
            for (barcode: Barcode in it) {
                Log.d("FindIt raw value", barcode.rawValue.toString())
                val data = barcode.rawValue
                if (data != null) {
                    qrCodeOutput = "$data"
                    val gotoCampusInfo = Intent(this, PlaceInfoActivity::class.java)
                    gotoCampusInfo.putExtra(QR_CODE_KEY, qrCodeOutput)
                    gotoCampusInfo.putExtra(SRC_TO_GET_PLACE_DETAIL, "from_qr")
                    startActivity(gotoCampusInfo)
                } else {
                    Toast.makeText(this, "Invalid QR, please try again!", Toast.LENGTH_LONG).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Invalid QR, please try again!", Toast.LENGTH_LONG).show()
        }
    }
}
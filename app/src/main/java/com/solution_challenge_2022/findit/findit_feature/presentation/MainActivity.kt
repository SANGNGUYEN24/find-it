package com.solution_challenge_2022.findit.findit_feature.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.ActivityMainBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.qr_scanner.QrScannerActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView = binding.bottomNavView
        val floatingActionButton = binding.floatingActionButton

        bottomNavView.background = null
        bottomNavView.menu.getItem(1).isEnabled = false
//        bottomNavView.menu.getItem(1).title = resources.getString(R.string.bottom_nav_scan_qr)

        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavView, navController)

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment, R.id.settingFragment
        ).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        floatingActionButton.setOnClickListener{
            val intent = Intent(this@MainActivity, QrScannerActivity::class.java)
            startActivity(intent)
        }
    }
}
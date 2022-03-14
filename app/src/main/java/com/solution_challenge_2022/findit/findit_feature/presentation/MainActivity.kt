package com.solution_challenge_2022.findit.findit_feature.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.solution_challenge_2022.findit.R
import com.solution_challenge_2022.findit.databinding.ActivityMainBinding
import com.solution_challenge_2022.findit.findit_feature.presentation.ar_map.ArMapActivity
import com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.CampusInfoActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(
            com.google.android.material.R.anim.abc_slide_in_bottom,
            com.google.android.material.R.anim.abc_fade_out
        )

        val toolbar = binding.toolbar
        val bottomNavView = binding.bottomNavView
        val floatingActionButton = binding.floatingActionButton
        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)

        bottomNavView.background = null
        bottomNavView.menu.getItem(1).isEnabled = false // QR code scanner

        setSupportActionBar(toolbar)

        NavigationUI.setupWithNavController(bottomNavView, navController)

        // Show alert dialog
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, CampusInfoActivity::class.java))
            finish()
        }
    }

    // Customize toolbar with menu item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.arMap -> {
                startActivity(Intent(this@MainActivity, ArMapActivity::class.java))
//                startActivity(Intent(this@MainActivity, CampusActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
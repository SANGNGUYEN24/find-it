package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui.review

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.solution_challenge_2022.findit.databinding.ActivityReviewUploadBinding
import com.solution_challenge_2022.findit.util.Constant.Companion.REVIEW_FRAGMENT_TO_REVIEW_UPLOAD_RATING

class ReviewUploadActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewUploadBinding
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val currentUser = firebaseAuth.currentUser
        val profileUrl = currentUser?.photoUrl

        val profileImage = binding.profileImage
        val userName = binding.userName
        val ratingBar = binding.ratingBar
        val post = binding.post

        Glide.with(this).load(profileUrl)
            .placeholder(com.solution_challenge_2022.findit.R.drawable.placeholder)
            .into(profileImage)

        userName.text = currentUser?.displayName

        val rating = intent.getFloatExtra(REVIEW_FRAGMENT_TO_REVIEW_UPLOAD_RATING, 0.0f)
        ratingBar.rating = rating

        post.setOnClickListener {
            Toast.makeText(this, "This function will be developed later", Toast.LENGTH_LONG).show()
        }

    }

    // Handle when pressing back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
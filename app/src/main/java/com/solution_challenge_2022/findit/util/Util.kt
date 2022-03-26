package com.solution_challenge_2022.findit.util

import android.content.Context
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.firebase.auth.FirebaseAuth


fun getProgressDrawable(c: Context): CircularProgressDrawable{
    return CircularProgressDrawable(c).apply {
        strokeWidth = 5f
        centerRadius = 40f
        start()
    }
}

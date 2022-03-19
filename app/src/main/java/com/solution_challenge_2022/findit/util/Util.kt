package com.solution_challenge_2022.findit.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable


fun getProgressDrawable(c: Context): CircularProgressDrawable{
    return CircularProgressDrawable(c).apply {
        strokeWidth = 5f
        centerRadius = 40f
        start()
    }
}
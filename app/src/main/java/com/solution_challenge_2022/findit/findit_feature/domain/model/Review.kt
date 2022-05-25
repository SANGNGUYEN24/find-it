package com.solution_challenge_2022.findit.findit_feature.domain.model

import com.google.firebase.Timestamp

data class Review(
    val id : String? = null,
    val userName: String? = null,
    val profileUrl: String? = null,
    val timestamp: Timestamp? = null,
    val rating: Float? = null,
    val comment: String? = null,
    val reviewImageUrl: String? = null,
)


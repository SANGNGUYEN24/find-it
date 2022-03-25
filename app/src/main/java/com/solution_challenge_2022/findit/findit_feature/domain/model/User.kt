package com.solution_challenge_2022.findit.findit_feature.domain.model

import android.net.Uri

data class User(
    val uid: String? = null,
    val email: String? = null,
    val userName: String? = null,
    val profileUrl: Uri? = null
)

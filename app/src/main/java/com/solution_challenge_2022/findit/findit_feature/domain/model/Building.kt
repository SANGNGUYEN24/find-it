package com.solution_challenge_2022.findit.findit_feature.domain.model

data class Building(
    var imageLink: String? = null,
    val buildingName: String? = null,
    val buildingId: String? = null,
    val campusName: String? = null,
    val campusId: String? = null,
    var desc: String? = null,
    var starNum: Double? = null,
    var reviewNum: Int? = null
)

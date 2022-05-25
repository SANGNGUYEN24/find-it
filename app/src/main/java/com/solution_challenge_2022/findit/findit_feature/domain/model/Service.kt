package com.solution_challenge_2022.findit.findit_feature.domain.model

data class Service(
    val campusId: String? = null,
    val serviceId: String? = null,
    val serviceName: String? = null,
    val inChargeBuildingId: String? = null,
    var desc: String? = null,
    val imageLink: String? = null
)
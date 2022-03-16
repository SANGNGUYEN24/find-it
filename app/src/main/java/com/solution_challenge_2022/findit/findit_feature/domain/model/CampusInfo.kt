package com.solution_challenge_2022.findit.findit_feature.domain.model

data class CampusInfo(
    var campusName: String? = null,
    var mapLink: String? = null,
)

fun CampusInfo.toCampusInfo(): CampusInfo {
    return CampusInfo(campusName = campusName, mapLink = mapLink)
}

package com.solution_challenge_2022.findit.findit_feature.domain.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

interface BuildingDetailRepository {
    suspend fun getBuildingDetail(campusId: String, buildingId: String): Building
}
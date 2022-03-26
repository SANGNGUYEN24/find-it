package com.solution_challenge_2022.findit.findit_feature.domain.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.PlaceInfo
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service

interface PlaceInfoRepository {
    suspend fun getPlaceInfo(campusId: String): PlaceInfo
    suspend fun getCurrentBuilding(buildingId: String): Building
    suspend fun getPopularAreasList(campusId: String): List<Building>
    suspend fun getServiceList(campusId: String): List<Service>
}
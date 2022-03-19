package com.solution_challenge_2022.findit.findit_feature.domain.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo

interface CampusInfoRepository {
    suspend fun getCampusInfo(campusId : String): CampusInfo
    suspend fun getCurrentBuilding(buildingId: String): Building
    suspend fun getPopularAreasList(campusId: String) : List<Building>
}
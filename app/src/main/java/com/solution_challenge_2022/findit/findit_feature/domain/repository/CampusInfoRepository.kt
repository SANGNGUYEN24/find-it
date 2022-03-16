package com.solution_challenge_2022.findit.findit_feature.domain.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.util.Resource
import kotlinx.coroutines.flow.Flow

interface CampusInfoRepository {
    suspend fun getCampusInfo(campusId : String): CampusInfo
    suspend fun getBuildingList(campusId: String, buildingId: String) : List<Building>
}
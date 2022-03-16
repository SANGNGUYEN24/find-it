package com.solution_challenge_2022.findit.findit_feature.domain.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo

interface CampusInfoRepository {
    suspend fun getCampusInfo(): CampusInfo
    suspend fun getBuildingList() : List<Building>
}
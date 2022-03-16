package com.solution_challenge_2022.findit.findit_feature.data.repository

import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository

class CampusInfoImpl : CampusInfoRepository{
    override suspend fun getCampusInfo(): CampusInfo {
        TODO("Not yet implemented")
    }
    override suspend fun getBuildingList(): List<Building> {
        TODO("Not yet implemented")
    }
}
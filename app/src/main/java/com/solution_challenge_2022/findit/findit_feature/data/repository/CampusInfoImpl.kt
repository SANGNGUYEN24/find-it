package com.solution_challenge_2022.findit.findit_feature.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import com.solution_challenge_2022.findit.util.Constant
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CampusInfoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : CampusInfoRepository {
    override suspend fun getCampusInfo(campusId: String): CampusInfo {
        return db.collection(Constant.CAMPUS)
            .document(campusId).get().await().toObject(CampusInfo::class.java)!!
    }

    override suspend fun getBuildingList(campusId: String, buildingId: String): List<Building> {
        TODO("Not yet implemented")
    }

}
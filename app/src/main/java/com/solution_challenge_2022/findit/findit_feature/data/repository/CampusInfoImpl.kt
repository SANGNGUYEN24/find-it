package com.solution_challenge_2022.findit.findit_feature.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import com.solution_challenge_2022.findit.util.Constant.Companion.BUILDING_INFO
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS
import com.solution_challenge_2022.findit.util.Constant.Companion.SERVICE
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CampusInfoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : CampusInfoRepository {
    private lateinit var campusId: String
    override suspend fun getCampusInfo(campusId: String): CampusInfo {
        this.campusId = campusId
        return db.collection(CAMPUS)
            .document(campusId).get().await().toObject(CampusInfo::class.java)!!
    }

    override suspend fun getCurrentBuilding(buildingId: String): Building {
        return db.collection(CAMPUS)
            .document(this.campusId).collection(BUILDING_INFO).document(buildingId).get()
            .await()
            .toObject(Building::class.java)!!
    }

    override suspend fun getPopularAreasList(campusId: String): List<Building> {
        return db.collection(CAMPUS).document(campusId).collection(BUILDING_INFO).get().await()
            .toObjects(Building::class.java)
    }

    override suspend fun getServiceList(campusId: String): List<Service> {
        return db.collection(CAMPUS).document(campusId).collection(SERVICE).get().await()
            .toObjects(Service::class.java)
    }
}
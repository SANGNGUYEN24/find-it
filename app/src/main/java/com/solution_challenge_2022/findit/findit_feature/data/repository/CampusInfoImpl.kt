package com.solution_challenge_2022.findit.findit_feature.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import com.solution_challenge_2022.findit.util.Constant.Companion.FIRESTORE_CAMPUS
import javax.inject.Inject

class CampusInfoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : CampusInfoRepository {
    override suspend fun getCampusInfo(campusId: String): CampusInfo {
        val temp = db.collection(FIRESTORE_CAMPUS).document(campusId).get()
        Log.d("GetCampusInfo", temp.toString())
        return CampusInfo()
    }

    override suspend fun getBuildingList(campusId: String, buildingId: String): List<Building> {
        TODO("Not yet implemented")
    }

}
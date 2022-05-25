package com.solution_challenge_2022.findit.findit_feature.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.repository.BuildingDetailRepository
import com.solution_challenge_2022.findit.util.Constant.Companion.BUILDING_INFO
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BuildingDetailImpl @Inject constructor(
    private val db: FirebaseFirestore
) : BuildingDetailRepository {
    override suspend fun getBuildingDetail(campusId: String, buildingId: String): Building {
        val building =  db.collection(CAMPUS).document(campusId).collection(BUILDING_INFO)
            .document(buildingId).get().await().toObject(Building::class.java)!!
        Log.d("Find It BuildingDetailImpl", building.toString())
        return building
    }
}
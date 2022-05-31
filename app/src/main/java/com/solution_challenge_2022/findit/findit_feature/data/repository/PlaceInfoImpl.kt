package com.solution_challenge_2022.findit.findit_feature.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.PlaceInfo
import com.solution_challenge_2022.findit.findit_feature.domain.model.Review
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service
import com.solution_challenge_2022.findit.findit_feature.domain.repository.PlaceInfoRepository
import com.solution_challenge_2022.findit.util.Constant
import com.solution_challenge_2022.findit.util.Constant.Companion.BUILDING_INFO
import com.solution_challenge_2022.findit.util.Constant.Companion.CAMPUS
import com.solution_challenge_2022.findit.util.Constant.Companion.REVIEW
import com.solution_challenge_2022.findit.util.Constant.Companion.SERVICE
import com.solution_challenge_2022.findit.util.Constant.Companion.USER_REVIEW
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PlaceInfoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : PlaceInfoRepository {
    private lateinit var campusId: String
    override suspend fun getPlaceInfo(campusId: String): PlaceInfo {
        this.campusId = campusId
        return db.collection(CAMPUS)
            .document(campusId).get().await().toObject(PlaceInfo::class.java)!!
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

    override suspend fun getEntertainmentVenueList(campusId: String): List<Building> {
        return db.collection(CAMPUS).document(campusId).collection(Constant.ENTERTAINMENT_VENUE).get().await()
            .toObjects(Building::class.java)
    }

    override suspend fun getServiceList(campusId: String): List<Service> {
        return db.collection(CAMPUS).document(campusId).collection(SERVICE).get().await()
            .toObjects(Service::class.java)
    }

    override suspend fun getReviewList(campusId: String): List<Review> {
        return db.collection(REVIEW).document(campusId).collection(USER_REVIEW).get().await()
            .toObjects(Review::class.java)
    }
}



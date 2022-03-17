package com.solution_challenge_2022.findit.findit_feature.data.repository

import android.util.Log
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
//        lateinit var campusName: String
//        lateinit var mapLink: String
//        lateinit var campusInfo : CampusInfo
        val docRef = db.collection(Constant.FIRESTORE_CAMPUS)
            .document(campusId)
        val campusInfo = docRef.get().await().toObject(CampusInfo::class.java)!!
//        db.collection(Constant.FIRESTORE_CAMPUS)
//            .document(campusId).get()
//            .addOnCompleteListener { document ->
//                campusName = document.result.data?.get("name").toString()
//                mapLink = document.result.data?.get("mapLink").toString()
//                campusInfo = CampusInfo(campusName, mapLink)
//                Log.d("CampusInfoImpl", campusInfo.toString())
////                Log.d("CampusInfoImpl Document data", document.result.data.toString())
////                Log.d("CampusInfoImpl campus name", campusName)
////                Log.d("CampusInfoImpl map link", mapLink)
////                return
////            }.addOnFailureListener { exception ->
////                Log.d("CampusInfoImpl getCampusInfo", "get failed with ", exception)
////            }
                        return campusInfo
            }

        override suspend fun getBuildingList(campusId: String, buildingId: String): List<Building> {
            TODO("Not yet implemented")
        }

    }
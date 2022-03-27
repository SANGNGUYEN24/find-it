package com.solution_challenge_2022.findit.findit_feature.presentation.place_info.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.PlaceInfo
import com.solution_challenge_2022.findit.findit_feature.domain.model.Review
import com.solution_challenge_2022.findit.findit_feature.domain.model.Service
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.campus_info.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val getCampusInfoUseCase: GetCampusInfoUseCase,
    private val getCurrentBuildingUseCase: GetCurrentBuildingUseCase,
    private val getPopularAreasListUseCase: GetPopularAreasListUseCase,
    private val getServiceListUseCase: GetServiceListUseCase,
    private val getReviewListUseCase: GetReviewListUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    // TODO handle campusId, save it into viewmodel
    private lateinit var campusId: String

    private val _qrCodeData = MutableLiveData<String>()
    val qrCodeData: LiveData<String> get() = _qrCodeData

    private val _campusInfo = MutableLiveData<PlaceInfo?>()
    val placeInfo: LiveData<PlaceInfo?> get() = _campusInfo

    private val _building = MutableLiveData<Building?>()
    val currentBuilding: LiveData<Building?> get() = _building

    private val _popularAreasList = MutableLiveData<List<Building>?>()
    val popularAreasList: LiveData<List<Building>?> get() = _popularAreasList

    private val _serviceList = MutableLiveData<List<Service>?>()
    val serviceList: LiveData<List<Service>?> get() = _serviceList

    private val _reviewList = MutableLiveData<List<Review>?>()
    val reviewList: LiveData<List<Review>?> get() = _reviewList

    private val _srcToGetData = MutableLiveData<String?>()
    val srcToGetData: LiveData<String?> get() = _srcToGetData

    private val _profileUrl = MutableLiveData<Uri?>()
    val profileUrl: LiveData<Uri?> get() = _profileUrl

    init {
        val currentUser = firebaseAuth.currentUser
        _profileUrl.value = currentUser?.photoUrl
    }

    private fun getCampusDestinationInfo(campusId: String, buildingId: String) {
        viewModelScope.launch {
            launch {
                _campusInfo.value = getCampusInfoUseCase(campusId)
            }
            launch {
                _building.value = getCurrentBuildingUseCase(buildingId)
            }
            launch {
                _popularAreasList.value = getPopularAreasListUseCase(campusId)
            }
            launch {
                _serviceList.value = getServiceListUseCase(campusId)
            }
            launch {
                _reviewList.value = getReviewListUseCase(campusId)
            }
        }
    }

    fun updateQrCodeData(input: String) {
        _qrCodeData.value = input
        val contentList = input.split("-")
        Log.d("contentList", contentList.toString())
        getCampusDestinationInfo(campusId = contentList[0], buildingId = contentList[1])
    }

    fun updateSrcToGetData(input: String) {
        _srcToGetData.value = input
    }
}


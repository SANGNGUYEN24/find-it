package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCampusInfoUserCase
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCurrentBuildingUseCase
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetPopularAreasListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val getCampusInfoUserCase: GetCampusInfoUserCase,
    private val getCurrentBuildingUseCase: GetCurrentBuildingUseCase,
    private val getPopularAreasListUseCase: GetPopularAreasListUseCase
) : ViewModel() {
    private val _qrCodeData = MutableLiveData("Scan QR code to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData

    private val _campusInfo = MutableLiveData<CampusInfo?>()
    val campusInfo: LiveData<CampusInfo?> get() = _campusInfo

    private val _building = MutableLiveData<Building?>()
    val building: LiveData<Building?> get() = _building

    private val _popularAreasList = MutableLiveData<List<Building>?>()
    val popularAreasList: LiveData<List<Building>?> get() = _popularAreasList

    private fun getCampusDestinationInfo(campusId: String, buildingId: String) {
        viewModelScope.launch {
            launch {
                _campusInfo.value = getCampusInfoUserCase(campusId)
            }
            launch {
                _building.value = getCurrentBuildingUseCase(buildingId)
            }
            launch {
                _popularAreasList.value = getPopularAreasListUseCase(campusId)
            }
        }
    }

    fun updateQrCodeData(input: String) {
        _qrCodeData.value = input
        val contentList = input.split("-")
        Log.d("contentList", contentList.toString())
        getCampusDestinationInfo(campusId = contentList[0], buildingId = contentList[1])
    }
}

// Edit
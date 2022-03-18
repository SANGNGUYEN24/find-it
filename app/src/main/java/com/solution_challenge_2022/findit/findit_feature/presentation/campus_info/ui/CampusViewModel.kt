package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCampusInfoUserCase
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCurrentBuildingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val getCampusInfoUserCase: GetCampusInfoUserCase,
    private val getCurrentBuildingUseCase: GetCurrentBuildingUseCase
) : ViewModel() {
    private val _qrCodeData = MutableLiveData("Scan QR code to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData

    private val _campusInfo = MutableLiveData<CampusInfo?>()
    val campusInfo: MutableLiveData<CampusInfo?> get() = _campusInfo

    private val _building = MutableLiveData<Building?>()
    val building: MutableLiveData<Building?> get() = _building

    private fun getCampusInfo(campusId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _campusInfo.value = getCampusInfoUserCase(campusId)
        }
    }

    private fun getCurrentBuilding(buildingId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _building.value = getCurrentBuildingUseCase(buildingId)
        }
    }

    fun updateQrCodeData(input: String) {
        _qrCodeData.value = input
        val contentList = input.split("-")
        Log.d("contentList[0]", contentList[0])
        getCampusInfo(contentList[0])
        getCurrentBuilding(contentList[1])
    }
}
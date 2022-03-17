package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCampusInfoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val getCampusInfoUserCase: GetCampusInfoUserCase,
) : ViewModel() {
    private val _qrCodeData = MutableLiveData("Scan QR code to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData

    private val _campusInfo = MutableLiveData<CampusInfo?>()
    val campusInfo: MutableLiveData<CampusInfo?> get() = _campusInfo

    init {
//      qrCodeData.value?.let { getCampusInfo(it) }
        getCampusInfo("hcmut")
    }

    private fun getCampusInfo(campusId: String) {
        runBlocking {
            _campusInfo.value = getCampusInfoUserCase("hcmut")
        }
    }

    fun updateQrCodeData(input: String) {
        _qrCodeData.value = input
    }
}
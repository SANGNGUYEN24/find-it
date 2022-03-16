package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.GetCampusInfoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val getCampusInfoUserCase: GetCampusInfoUserCase,
) : ViewModel() {
    private val _qrCodeData = MutableLiveData("Scan QR code to explore campus")
    val qrCodeData: LiveData<String> get() = _qrCodeData

    private val _campusName = MutableLiveData<String>()
    val campusName: LiveData<String> get() = _campusName

    private val _mapLink = MutableLiveData<String>()
    val mapLink: LiveData<String> get() = _mapLink

    init {
        getCampusInfo()
    }

    private fun getCampusInfo() {
        getCampusInfoUserCase("hcmut")
    }

    fun updateQrCodeData(input: String) {
        _qrCodeData.value = input
    }
}
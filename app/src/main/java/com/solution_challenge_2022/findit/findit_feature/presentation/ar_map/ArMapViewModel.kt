package com.solution_challenge_2022.findit.findit_feature.presentation.ar_map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArMapViewModel : ViewModel() {
    private val _currentBuildingId = MutableLiveData<String?>()
    val currentBuildingId: LiveData<String?> get() = _currentBuildingId

    private val _destinationBuildingId = MutableLiveData<String?>()
    val destinationBuildingId: LiveData<String?> get() = _destinationBuildingId

    fun updateArMapData(currentBuildingId: String, destinationBuildingId: String) {
        _currentBuildingId.value = currentBuildingId
        _destinationBuildingId.value = destinationBuildingId
    }

}
package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.full_map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FullMapViewModel : ViewModel() {
    private val _mapLink = MutableLiveData<String?>()
    val mapLink: LiveData<String?> get() = _mapLink

    fun updateMapLink(mapLink: String) {
        _mapLink.value = mapLink
    }
}
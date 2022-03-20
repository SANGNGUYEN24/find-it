package com.solution_challenge_2022.findit.findit_feature.presentation.campus_info.ui.building_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building
import com.solution_challenge_2022.findit.findit_feature.domain.use_case.building_detail.GetBuildingDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingDetailViewModel @Inject constructor(
    private val getBuildingDetailUseCase: GetBuildingDetailUseCase
) : ViewModel() {
    private val _building = MutableLiveData<Building?>()
    val building: LiveData<Building?> get() = _building

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getBuildingDetail(campusId: String, buildingId: String) {
        Log.d("Find It BuildingDetailViewModel", "$campusId, $buildingId")
        viewModelScope.launch {
            Log.d("Find It BuildingDetailViewModel", "Here")
            launch {
                _building.value = getBuildingDetailUseCase(campusId, buildingId)
            }
        }
    }
}
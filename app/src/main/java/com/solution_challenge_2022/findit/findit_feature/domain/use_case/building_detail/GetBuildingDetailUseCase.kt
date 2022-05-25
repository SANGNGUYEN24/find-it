package com.solution_challenge_2022.findit.findit_feature.domain.use_case.building_detail

import com.solution_challenge_2022.findit.findit_feature.domain.repository.BuildingDetailRepository
import javax.inject.Inject

class GetBuildingDetailUseCase @Inject constructor(
    private val buildingDetailRepository: BuildingDetailRepository
) {
    suspend operator fun invoke(campusId: String, buildingId: String) =
        buildingDetailRepository.getBuildingDetail(campusId, buildingId)
}
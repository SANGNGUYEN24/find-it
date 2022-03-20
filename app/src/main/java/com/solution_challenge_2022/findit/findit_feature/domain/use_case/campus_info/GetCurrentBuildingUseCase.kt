package com.solution_challenge_2022.findit.findit_feature.domain.use_case.campus_info

import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import javax.inject.Inject

class GetCurrentBuildingUseCase @Inject constructor(
    private val campusInfoRepository: CampusInfoRepository
) {
    suspend operator fun invoke(buildingId: String) =
        campusInfoRepository.getCurrentBuilding(buildingId)
}
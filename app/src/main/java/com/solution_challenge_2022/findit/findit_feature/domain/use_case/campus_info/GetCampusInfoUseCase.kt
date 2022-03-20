package com.solution_challenge_2022.findit.findit_feature.domain.use_case.campus_info

import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import javax.inject.Inject

class GetCampusInfoUseCase @Inject constructor(
    private val campusInfoRepository: CampusInfoRepository
) {
    suspend operator fun invoke(campusId: String) = campusInfoRepository.getCampusInfo(campusId)
}
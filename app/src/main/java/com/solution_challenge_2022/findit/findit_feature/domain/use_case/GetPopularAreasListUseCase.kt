package com.solution_challenge_2022.findit.findit_feature.domain.use_case

import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import javax.inject.Inject

class GetPopularAreasListUseCase @Inject constructor(
    private val campusInfoRepository: CampusInfoRepository
) {
    suspend operator fun invoke(campusId: String) =
        campusInfoRepository.getPopularAreasList(campusId)
}
package com.solution_challenge_2022.findit.findit_feature.domain.use_case.campus_info

import com.solution_challenge_2022.findit.findit_feature.domain.repository.PlaceInfoRepository
import javax.inject.Inject

class GetReviewListUseCase @Inject constructor(
    private val placeInfoRepository: PlaceInfoRepository
) {
    suspend operator fun invoke(campusId: String) =
        placeInfoRepository.getReviewList(campusId)
}
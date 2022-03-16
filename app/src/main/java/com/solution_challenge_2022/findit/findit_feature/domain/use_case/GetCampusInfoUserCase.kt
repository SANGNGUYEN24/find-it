package com.solution_challenge_2022.findit.findit_feature.domain.use_case

import com.solution_challenge_2022.findit.findit_feature.domain.model.CampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.model.toCampusInfo
import com.solution_challenge_2022.findit.findit_feature.domain.repository.CampusInfoRepository
import com.solution_challenge_2022.findit.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCampusInfoUserCase @Inject constructor(
    private val campusInfoRepository: CampusInfoRepository
) {
    operator fun invoke(campusId: String): Flow<Resource<CampusInfo>> = flow {
        try {
            emit(Resource.Loading())
            val campusInfo = campusInfoRepository.getCampusInfo(campusId).toCampusInfo()
            emit(Resource.Success(campusInfo))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your connection!"))
        }
    }
}
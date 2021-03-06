package com.solution_challenge_2022.findit.util

import com.solution_challenge_2022.findit.R

class Constant {
    companion object {
        const val CAMERA_PERMISSION_CODE = 100
        const val READ_STORAGE_PERMISSION_CODE = 101
        const val WRITE_STORAGE_PERMISSION_CODE = 102
        val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )

        // Firebase
        const val CAMPUS = "campus"
        const val REVIEW = "review"
        const val BUILDING_INFO = "buildingInfo"
        const val ENTERTAINMENT_VENUE = "entertainmentVenue"
        const val SERVICE = "service"
        const val USER_REVIEW = "userReview"

        // Key to transfer data between activities
        const val QR_CODE_KEY = "qr_code_key"
        const val CAMPUS_INFO_TO_BUILDING_DETAIL_CAMPUS_ID =
            "campus_info_to_building_detail_campus_id"
        const val CAMPUS_INFO_TO_BUILDING_DETAIL_BUILDING_ID =
            "campus_info_to_building_detail_building_id"
        const val CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_ID =
            "campus_info_to_building_detail_current_building_id"
        const val CAMPUS_INFO_TO_BUILDING_DETAIL_CURRENT_BUILDING_NAME =
            "campus_info_to_building_detail_current_building_name"
        const val CAMPUS_INFO_TO_FULL_MAP_LINK =
            "campus_info_to_full_map_link"
        const val BUILDING_DETAIL_TO_AR_MAP_CURRENT_BUILDING_ID =
            "building_detail_to_ar_map_current_building_id"
        const val BUILDING_DETAIL_TO_AR_MAP_DESTINATION_ID =
            "building_detail_to_ar_map_destination_id"
        const val SRC_TO_GET_PLACE_DETAIL = "src_to_get_place_detail"
        const val REVIEW_FRAGMENT_TO_REVIEW_UPLOAD_RATING = "review_fragment_to_review_upload_rating"
    }
}
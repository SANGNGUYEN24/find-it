package com.solution_challenge_2022.findit.util

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

class DataMethod {

    fun updateData() {
        val db = FirebaseFirestore.getInstance()

        val buildingA1 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA1.jpg?alt=media&token=b49c8764-05e6-46a1-a05b-7f0a62cc9d30",
            buildingName = "The Building A1",
            buildingId = "a1",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building A1. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingA2 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA2.jpg?alt=media&token=1a128863-857e-4f1c-8950-b4d08e24b5e8",
            buildingName = "The Building A2",
            buildingId = "a2",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building A2. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingA4 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA4.jpg?alt=media&token=09788484-415c-455e-bd9b-e399306a1308",
            buildingName = "The Building A4 - OISP",
            buildingId = "a4",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building A4. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingA5 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA5.jpg?alt=media&token=2c7a10b1-d140-4bfa-95c9-552c077b8468",
            buildingName = "The Building A5",
            buildingId = "a5",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building A5. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB1A = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB1A.jpg?alt=media&token=84cdb88a-455a-4856-96dd-3a02f1977f60",
            buildingName = "The Building B1-A",
            buildingId = "b1a",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B1-A. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )
        val buildingB1B = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB1B.jpg?alt=media&token=c1310f98-391f-4a4d-902d-f6ae7e9b62f2",
            buildingName = "The Building B1-B",
            buildingId = "b1b",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B1-B. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB2 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB2.jpg?alt=media&token=714c8c91-2b7f-4334-a2d5-57ac77487313",
            buildingName = "The Building B2",
            buildingId = "b2",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B2. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB3 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB3.jpg?alt=media&token=6b168d78-1652-4189-a88a-a7ee25c52ee1",
            buildingName = "The Building B3",
            buildingId = "b3",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B3. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB4 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB4.jpg?alt=media&token=2a354477-f375-4cff-bad0-45ecc998438c",
            buildingName = "The Building B4",
            buildingId = "b4",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B4. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB6 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB6.jpg?alt=media&token=199c9a3d-899f-45d3-9d62-cc3e31fcadb7",
            buildingName = "The Building B6",
            buildingId = "b6",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B6. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingGate1 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FGate1.jpg?alt=media&token=7fe342b1-3bc1-432b-8f14-726b2288c47d",
            buildingName = "The Main Gate",
            buildingId = "gate1",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Main Gate. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingFootballPitch = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FS%C3%A2n%20b%C3%B3ng%20%C4%91%C3%A1.jpg?alt=media&token=4b693296-5b05-4041-825e-dc351b254052",
            buildingName = "Football Pitch",
            buildingId = "football_pitch",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "Football Pitch. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )


        val buildingA3 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fa3.jpg?alt=media&token=55866086-ea20-4159-872c-747b1fddcc28",
            buildingName = "The Building A3",
            buildingId = "a3",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building A3. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB8 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fb8.jpg?alt=media&token=970b17d8-0208-4253-9f77-d57450943e0e",
            buildingName = "The Building B8",
            buildingId = "b8",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B8. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingB9 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fb9.jpg?alt=media&token=5a95a12a-fdab-4bab-9e5f-decce35b07a6",
            buildingName = "The Building B9",
            buildingId = "b9",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building B9. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingC1 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fc1.jpg?alt=media&token=22c90586-7699-460e-8cbd-f60e27f27c90",
            buildingName = "The Building C1",
            buildingId = "c1",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "The Building c1. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingGate2 = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fgate2.jpg?alt=media&token=2d8e2e52-e063-4148-ae59-2c6af46306db",
            buildingName = "Gate 2",
            buildingId = "gate2",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "Gate 2. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val buildingBasketballPitch = Building(
            imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fsanbong.jpg?alt=media&token=81a0c65b-f9b2-4b5e-b80c-51e64986c576",
            buildingName = "Basketball Pitch",
            buildingId = "basketball_pitch",
            campusName = "Ho Chi Minh City University of Technology",
            campusId = "hcmut",
            desc = "Basketball Pitch. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                    "\n" +
                    "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
        )

        val list = listOf(
            buildingA1,
            buildingA2,
            buildingA3,
            buildingA4,
            buildingA5,
            buildingB1A,
            buildingB1B,
            buildingB2,
            buildingB3,
            buildingB4,
            buildingB6,
            buildingGate1,
            buildingFootballPitch,
            buildingB8,
            buildingB9,
            buildingC1,
            buildingGate2,
            buildingBasketballPitch
        )

        for (i in list) {
            db.collection(Constant.CAMPUS).document("hcmut").collection(Constant.BUILDING_INFO)
                .document(i.buildingId.toString()).set(i).addOnSuccessListener {
                    Log.d("Find It uploaded: ", i.buildingId.toString())
                }
        }

    }

}
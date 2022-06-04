package com.solution_challenge_2022.findit.util

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.solution_challenge_2022.findit.findit_feature.domain.model.Building

class DataMethod {
    private val db = FirebaseFirestore.getInstance()

    private val buildingA1 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA1.jpg?alt=media&token=b49c8764-05e6-46a1-a05b-7f0a62cc9d30",
        buildingName = "The Building A1",
        buildingId = "a1",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.8,
        reviewNum = 100,
        desc = "The Building A1. The Department has the function of advising and assisting the Principal in the school's short-term and long-term training plans. Organizing, directing, inspecting and supervising all teaching and learning activities in schools, undergraduate and graduate formal training systems. Research and propose training objectives, training systems, industry structure, training scale and methods, as well as the development of necessary facilities for teaching and learning.  In addition, the department is responsible for coordinating with units in the school to organize the teaching schedule, register for tests, exams and evaluate learning results. Along with monitoring and checking the implementation of teaching and learning regulations, promptly proposing necessary issues, the reporter also collects, statistics, processes and stores academic information provided by the faculties. providing, as well as building and guiding the faculties to set up uniform academic management statistical forms throughout the school."
    )

    private val buildingA2 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA2.jpg?alt=media&token=1a128863-857e-4f1c-8950-b4d08e24b5e8",
        buildingName = "The Building A2",
        buildingId = "a2",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.7,
        reviewNum = 56,
        desc = "The Buidling A2 as known as Central Library of Ho Chi Minh City University of Technology is one of the largest libraries in the city today with hundreds of thousands of regularly updated books, the Central Library of Vietnam National University Ho Chi Minh City. Ho Chi Minh City consists of three floors containing books, rooms used for group study, reading room, and self-study area. In addition, the Central Library of the National University of Ho Chi Minh City. HCM also provides on-site printing and copying equipment, audio-visual equipment such as TVs, videos, Cassettes, CDs. In particular, the library also has a system to borrow and return electronic documents so that you can be proactive in your work."
    )


    private val buildingA3 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fa3.jpg?alt=media&token=55866086-ea20-4159-872c-747b1fddcc28",
        buildingName = "The Building A3",
        buildingId = "a3",
        starNum = 4.6,
        reviewNum = 12,
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Building A3. Computer Science was trained very early at the University of Technology, starting in 1977. Currently, the Faculty of Computer Science and Engineering is a leading faculty in the field of information technology in Vietnam and Now there are other faculties. training and research programs at undergraduate, master's and doctoral levels.  The Faculty of Engineering and Computer Science trains engineers according to international standards ABET (http://www.abet.org/) in two fields of Computer Science and Computer Engineering. These are the first and only two programs currently in Vietnam that meet the ABET international standards of the United States that leading companies in the world such as Intel, IBM, and Boeing all desire. In both Computer Science and Computer Engineering, there are specialized training directions in Software Engineering, Information Systems, Network & Data Security, Computer Structures & IC Design, Computer Networks , High performance computing & super big data."
    )

    private val buildingA4 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA4.jpg?alt=media&token=09788484-415c-455e-bd9b-e399306a1308",
        buildingName = "The Building A4 - OISP",
        buildingId = "a4",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.9,
        reviewNum = 120,
        desc = "The Building A4. The Office of International Studies Programs (OISP) was established in 2006, in order to professionalize international training activities at undergraduate and postgraduate levels of Ho Chi Minh City University of Technology (HCMUT) - University of Science and Technology. Science and technology. Hometown HCMC.  Up to now, OISP has been implementing dozens of international training programs, spanning the fields of engineering, technology, and administration.  OISP's affiliate partners are famous universities of Australia, USA, Japan, Switzerland... Students can choose to study full-time at HCMUT or transfer to foreign universities.  International training programs are taught entirely in English, taught by a team of excellent lecturers from HCMUT and partner universities. Therefore, it attracts more and more students not only from Vietnam but also international students from France, Japan, Korea, Philippines, Laos, Burma, Pakistan, Egypt, Nigeria, Zambia... multicultural learning environment at HCMUT."
    )

    private val buildingA5 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FA5.jpg?alt=media&token=2c7a10b1-d140-4bfa-95c9-552c077b8468",
        buildingName = "The Building A5",
        buildingId = "a5",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.2,
        reviewNum = 94,
        desc = "Hall A5 is the venue for major and important events at the University of Technology Ho Chi Minh city: Graduation ceremony, Admission ceremony, annual meetings,..."
    )

    private val buildingB1A = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB1A.jpg?alt=media&token=84cdb88a-455a-4856-96dd-3a02f1977f60",
        buildingName = "The Building B1-A",
        buildingId = "b1a",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.8,
        reviewNum = 230,
        desc = "ELECTRICAL & ELECTRONIC ENGINEERING Faculty The program is a part of the National Project and operates according to the latest curriculum from UIUC (USA).   The program equips students with modern knowledge, professional skills and abilities to use informatics & communication tools. It was developed to apply advanced training technology from United States partner universities."
    )
    private val buildingB1B = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB1B.jpg?alt=media&token=c1310f98-391f-4a4d-902d-f6ae7e9b62f2",
        buildingName = "The Building B1-B",
        buildingId = "b1b",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.5,
        reviewNum = 15,
        desc = "The Building B1-B. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB2 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB2.jpg?alt=media&token=714c8c91-2b7f-4334-a2d5-57ac77487313",
        buildingName = "The Building B2",
        buildingId = "b2",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        starNum = 4.5,
        reviewNum = 300,
        desc = "The Building B2. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB3 = Building(
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB3.jpg?alt=media&token=6b168d78-1652-4189-a88a-a7ee25c52ee1",
        buildingName = "The Building B3",
        buildingId = "b3",
        campusName = "Ho Chi Minh City University of Technology",
        starNum = 4.7,
        reviewNum = 17,
        campusId = "hcmut",
        desc = "The Building B3. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB4 = Building(
        starNum = 4.8,
        reviewNum = 100,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB4.jpg?alt=media&token=2a354477-f375-4cff-bad0-45ecc998438c",
        buildingName = "The Building B4",
        buildingId = "b4",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Building B4. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB6 = Building(
        starNum = 4.6,
        reviewNum = 88,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FB6.jpg?alt=media&token=199c9a3d-899f-45d3-9d62-cc3e31fcadb7",
        buildingName = "The Building B6",
        buildingId = "b6",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Building B6. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingGate1 = Building(
        starNum = 4.8,
        reviewNum = 111,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fmain-gate.jpg?alt=media&token=eb507d02-4348-4ccd-a865-0801ec40251d",
        buildingName = "The Main Gate",
        buildingId = "gate1",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Main Gate. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingFootballPitch = Building(
        starNum = 4.3,
        reviewNum = 89,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2FS%C3%A2n%20b%C3%B3ng%20%C4%91%C3%A1.jpg?alt=media&token=4b693296-5b05-4041-825e-dc351b254052",
        buildingName = "Football Pitch",
        buildingId = "football_pitch",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "Football Pitch. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB8 = Building(
        starNum = 4.8,
        reviewNum = 91,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fb8.jpg?alt=media&token=970b17d8-0208-4253-9f77-d57450943e0e",
        buildingName = "The Building B8",
        buildingId = "b8",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Building B8. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingB9 = Building(
        starNum = 4.4,
        reviewNum = 187,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fb9.jpg?alt=media&token=5a95a12a-fdab-4bab-9e5f-decce35b07a6",
        buildingName = "The Building B9",
        buildingId = "b9",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "The Building B9. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingC1 = Building(
        starNum = 4.8,
        reviewNum = 100,
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
        starNum = 4.3,
        reviewNum = 18,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fgate2.jpg?alt=media&token=2d8e2e52-e063-4148-ae59-2c6af46306db",
        buildingName = "Gate 2",
        buildingId = "gate2",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "Gate 2. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val buildingBasketballPitch = Building(
        starNum = 5.0,
        reviewNum = 46,
        imageLink = "https://firebasestorage.googleapis.com/v0/b/sc2022-find-it.appspot.com/o/hcmut%2Fbuilding%2Fsanbong.jpg?alt=media&token=81a0c65b-f9b2-4b5e-b80c-51e64986c576",
        buildingName = "Basketball Pitch",
        buildingId = "basketball_pitch",
        campusName = "Ho Chi Minh City University of Technology",
        campusId = "hcmut",
        desc = "Basketball Pitch. The Ho Chi Minh City University of Technology (abbreviation: HCMUT Vietnamese: Trường Đại học Bách khoa, Đại học Quốc gia Thành phố Hồ Chí Minh)[1] is a key member university of Vietnam National University, Ho Chi Minh City and is the flagship university in technology teaching and research activities in Vietnam.\n" +
                "\n" +
                "HCMUT is a center of technology - industry and management training. Up to May 2005, HCMUT has 11 faculties, 14 research and development (R&D) centers, 4 training centers, 10 functioning offices and one joint-stock company. During the past 40 years since Vietnam's unification, 60,000 Engineers and Bachelors have graduated from HCMUT. Up to now, HCMUT have trained 60,000 Engineers and Bachelors, more than 10,000 Masters and 200 Doctors."
    )

    private val list = listOf(
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

    fun updateBuildingInfoData() {
        for (i in list) {
            db.collection(Constant.CAMPUS).document("hcmut").collection(Constant.BUILDING_INFO)
                .document(i.buildingId.toString()).set(i, SetOptions.merge()).addOnSuccessListener {
                    Log.d("Find It uploaded: ", i.buildingId.toString())
                }
        }
    }

    fun updateEntertainmentVenues() {
        for (i in list.indices) {
            if (i % 2 == 0) {
                db.collection(Constant.CAMPUS).document("hcmut")
                    .collection(Constant.ENTERTAINMENT_VENUE)
                    .document(list[i].buildingId.toString()).set(list[i], SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d("Find It uploaded: ", list[i].buildingId.toString())
                    }
            }
        }
    }

    fun update() {
        for (i in 1 until list.size) {
            if (i % 5 == 0) {
                db.collection(Constant.CAMPUS).document("hcmut")
                    .collection(Constant.ENTERTAINMENT_VENUE)
                    .document(list[i].buildingId.toString()).set(list[i], SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d("Find It uploaded: ", list[i].buildingId.toString())
                    }
            }
        }
    }


}


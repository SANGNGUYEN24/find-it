package com.solution_challenge_2022.findit.findit_feature.domain.model

data class Building(
    val buildingName: String? = null,
    val desc: String? = null
)

fun Building.toBuilding(): Building {
    return Building(buildingName = buildingName, desc = desc)
}

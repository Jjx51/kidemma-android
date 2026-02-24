package com.kidemma.authentication.domain.model

data class FamilyUiModel (
    val id: Int,
    val nickname: String,
    val familyName: String,
    val members: List<FamilyMemberUiModel>
)
data class FamilyMemberUiModel(
    val id: Int,
    val name: String,
    val photo: Int
)
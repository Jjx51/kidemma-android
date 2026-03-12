package com.kidemma.authentication.domain.model

import androidx.annotation.DrawableRes

data class FamilyUiModel (
    val id: Int,
    val nickname: String,
    val familyName: String,
    val members: List<FamilyMemberUiModel>
)
data class FamilyMemberUiModel(
    val id: Int,
    val name: String,
    val gender: String,
    val role: String,
    @param:DrawableRes val image: Int,
    @param:DrawableRes val imageError: Int
)
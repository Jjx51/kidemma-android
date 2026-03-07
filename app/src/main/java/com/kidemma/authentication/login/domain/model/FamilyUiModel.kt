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
    @param:DrawableRes val image: Int
)
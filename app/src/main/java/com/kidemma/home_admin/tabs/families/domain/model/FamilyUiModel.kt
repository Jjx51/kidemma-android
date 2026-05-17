package com.kidemma.authentication.domain.model

import androidx.annotation.DrawableRes

/*
 * File: FamilyUiModel
 * Description: families UiModel
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 27/02/26
 * Last modified: 12/03/26
 */
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
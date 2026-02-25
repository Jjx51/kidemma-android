package com.kidemma.home_admin.domain.model

import com.kidemma.common.ui.models.ImageUiModel

/*
 * File: TopBarUserProfileUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
data class TopBarUserProfileUiModel(
    val profileName: String,
    val profileImage: ImageUiModel? = null,
    val hasUnreadNotifications: Boolean = false
)
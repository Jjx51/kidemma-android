package com.kidemma.homeAdmin.domain.model

import com.kidemma.common.ui.models.ImageUiModel

/*
 * File: TopBarUserProfileUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
data class TopBarProfileAvatarUiModel(
    val profileName: String,
    val profileImage: ImageUiModel? = null,
    val hasUnreadNotifications: Boolean = false
)

package com.kidemma.common.domain.models

import androidx.annotation.StringRes
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.ui.models.icons.IconUiModel

/*
 * File: KidemmaTopBarUiData
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
data class KidemmaTopBarUiModel(
    val appLogo: ImageUiModel,
    @param:StringRes val appName: Int,
    val notificationIcon: IconUiModel
)

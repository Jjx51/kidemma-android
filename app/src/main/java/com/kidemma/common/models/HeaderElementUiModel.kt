package com.kidemma.common.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/*
 * File: HeaderElementUiModel
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 24/02/26
 * Last modified: 09/03/26
 */

data class HeaderElementUiModel(
    @param:StringRes val title: Int,
    @param:DrawableRes val headerIconID: Int? = null,
    @param:StringRes val headerIconDescription: Int
)

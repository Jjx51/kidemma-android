package com.kidemma.common.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp

/*
 * File: ImageResource
 * Description: The purpose of this class is to be used along other ui Models to make it easy when
 * you need to use an image, instead of writing each time the 3 values , just use this model
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class ImageUiModel(
    @param:DrawableRes val resId: Int,
    @param:StringRes val contentDescription: Int,
    val size: Dp
)

package com.kidemma.common.ui.models.icons

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/*
 * File: IconUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class IconUiModel(
    @param:StringRes val contentDescription: Int,
    val icon: ImageVector,
    val tint: Color? = null,
)

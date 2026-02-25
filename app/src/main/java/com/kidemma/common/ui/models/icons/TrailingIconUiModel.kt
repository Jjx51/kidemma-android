package com.kidemma.common.ui.models.icons

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

/*
 * File: TrailingIconUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
sealed interface TrailingIconUiModel {
    data class Simple(
        val icon: ImageVector,
        @param:StringRes val contentDescription: Int,
    ) : TrailingIconUiModel

    data class Stateful(
        val activeIcon: ImageVector,
        @param:StringRes val activeContentDescription: Int,
        val inactiveIcon: ImageVector,
        @param:StringRes val inactiveContentDescription: Int
    ) : TrailingIconUiModel
}
package com.kidemma.homeAdmin.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.kidemma.common.navigation.AdminRoute

/*
 * File: AdminBottomNavItemUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
data class AdminBottomNavItemUiModel (
    val route: AdminRoute,
    @param:StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

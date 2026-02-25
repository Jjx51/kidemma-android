package com.kidemma.home_admin.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import com.kidemma.R
import com.kidemma.common.navigation.AdminRoute
import com.kidemma.home_admin.domain.model.AdminBottomNavItemUiModel

/*
 * File: AdminNavigationContentProviderImpl
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
object AdminNavigationContentProvider {

    fun getBottomNavigationItems(): List<AdminBottomNavItemUiModel> {
        return listOf(
            AdminBottomNavItemUiModel(
                route = AdminRoute.Home,
                label = R.string.admin_nav_home,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            AdminBottomNavItemUiModel(
                route = AdminRoute.Children,
                label = R.string.admin_nav_children,
                selectedIcon = Icons.Filled.Face,
                unselectedIcon = Icons.Outlined.Face
            ),
            AdminBottomNavItemUiModel(
                route = AdminRoute.Agenda,
                label = R.string.admin_nav_agenda,
                selectedIcon = Icons.Filled.DateRange,
                unselectedIcon = Icons.Outlined.DateRange
            ),
            AdminBottomNavItemUiModel(
                route = AdminRoute.Families,
                label = R.string.admin_nav_families,
                selectedIcon = Icons.Filled.Groups,
                unselectedIcon = Icons.Outlined.Groups
            ),
            AdminBottomNavItemUiModel(
                route = AdminRoute.Other,
                label = R.string.admin_nav_other,
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Outlined.Menu
            )
        )
    }
}
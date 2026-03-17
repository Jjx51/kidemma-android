package com.kidemma.homeAdmin.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kidemma.common.components.KidemmaTopAppBar
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaColors.BackgroundColorBottomItem
import com.kidemma.common.ui.theme.KidemmaColors.TextColorBottomItemSelected
import com.kidemma.homeAdmin.domain.model.AdminBottomNavItemUiModel
import com.kidemma.homeAdmin.domain.model.TopBarProfileAvatarUiModel

/*
 * File: AdminMainScreen
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
@Composable
fun AdminMainScreen(
    adminNavItems: List<AdminBottomNavItemUiModel> = AdminNavigationContentProvider.getBottomNavigationItems()
) {

    val adminNavController = rememberNavController()
    val navItems = remember { adminNavItems }

    val topBarProfileAvatarUiModel = TopBarProfileAvatarUiModel(
        profileName = "Jorge Nuñez",
        profileImage = null
    )

    Scaffold(
        topBar = {
            KidemmaTopAppBar(
                topBarProfileAvatarUiModel = topBarProfileAvatarUiModel,
                onProfileClick = {},
                onNotificationClick = {}
            )
        },
        bottomBar = {
            BottomBar(
                navController = adminNavController,
                navItems = navItems
            )
        },
        containerColor = KidemmaColors.Background
    ) { innerPadding ->
        AdminNavGraph(
            navController = adminNavController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    navItems: List<AdminBottomNavItemUiModel>
) {
    NavigationBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(item.route::class)
            } == true

            NavigationBarItem(
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = BackgroundColorBottomItem,
                    selectedIconColor = Color.White,
                    selectedTextColor = TextColorBottomItemSelected
                ),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(item.label)
                    )
                },
                label = { Text(stringResource(item.label)) }
            )
        }
    }
}

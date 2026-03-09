package com.kidemma.home_admin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kidemma.common.components.PlaceholderScreen
import com.kidemma.common.navigation.AdminRoute
import com.kidemma.home_admin.tabs.others.presentation.OthersScreen

/*
 * File: AdminNavGraph
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
@Composable
fun AdminNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AdminRoute.Home,
        modifier = modifier
    ) {

        composable<AdminRoute.Home> { PlaceholderScreen("Inicio") }

        composable<AdminRoute.Children> { PlaceholderScreen("Niños") }

        composable<AdminRoute.Agenda> { PlaceholderScreen("Agenda") }

        composable<AdminRoute.Families> { PlaceholderScreen("Familias") }

        composable<AdminRoute.Other> { OthersScreen(navController) }
    }
}
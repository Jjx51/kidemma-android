package com.kidemma.homeAdmin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kidemma.common.components.PlaceholderScreen
import com.kidemma.common.navigation.AdminRoute
import com.kidemma.homeAdmin.tabs.agenda.presentation.ui.AgendaTabScreen

/*
 * File: AdminNavGraph
 * Description: NavGraph for Admin section
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 26/02/26
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

        composable<AdminRoute.Agenda> {
            AgendaTabScreen()
        }

        composable<AdminRoute.Families> { PlaceholderScreen("Familias") }

        composable<AdminRoute.Other> { PlaceholderScreen("Otros") }
    }
}

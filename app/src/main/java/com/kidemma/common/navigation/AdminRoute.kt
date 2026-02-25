package com.kidemma.common.navigation

import kotlinx.serialization.Serializable

/*
 * File: AdminRoute
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
sealed interface AdminRoute {

    @Serializable
    data object Home : AdminRoute

    @Serializable
    data object Children : AdminRoute

    @Serializable
    data object Agenda : AdminRoute

    @Serializable
    data object Families : AdminRoute

    @Serializable
    data object Other : AdminRoute
}
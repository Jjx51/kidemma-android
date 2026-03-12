package com.kidemma.common.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object Splash : AppRoute

    @Serializable
    data object Onboarding : AppRoute

    @Serializable
    data object Login : AppRoute
    @Serializable
    data object AdminMain : AppRoute

    @Serializable
    data object UserMain : AppRoute

    @Serializable
    data object AdminPanel : AppRoute

    @Serializable
    data object AboutUs : AppRoute

    @Serializable
    data object Staff : AppRoute

    @Serializable
    data object Contact : AppRoute

    @Serializable
    data object Alliances: AppRoute

    @Serializable
    data object Complaints : AppRoute
}

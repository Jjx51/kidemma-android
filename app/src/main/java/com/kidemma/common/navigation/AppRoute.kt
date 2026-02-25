package com.kidemma.common.navigation

import kotlinx.serialization.Serializable

sealed interface  AppRoute{
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
}
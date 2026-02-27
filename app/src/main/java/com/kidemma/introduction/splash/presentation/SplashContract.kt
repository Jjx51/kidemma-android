package com.kidemma.introduction.splash.presentation

import com.kidemma.common.navigation.AppRoute

/*
 * File: SplashContract
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 28/10/25
 * Last modified: 28/10/25
 */
object SplashContract {

    sealed interface Intent {
        data object ValidateDestiny : Intent
    }

    sealed interface Effect {
        data class NavigateTo(val destination: AppRoute) : Effect
    }

}
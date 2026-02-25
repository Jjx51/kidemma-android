package com.kidemma.introduction.onboarding.presentation

import com.kidemma.common.navigation.AppRoute
import com.kidemma.introduction.onboarding.data.model.OnboardingPageUiModel

/*
 * File: OnboardingContract
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
object OnboardingContract {

    data class State(
        val currentPage: OnboardingPageUiModel,
        val currentPageIndex: Int,
        val totalPages: Int
    )


    sealed interface Intent {
        data object OnNextClicked : Intent
        data object OnSkipClicked : Intent
    }

    sealed interface Effect {
        data class NavigateTo(val screen: AppRoute) : Effect
    }

}
package com.kidemma.introduction.onboarding.presentation

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: OnboardingViewModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
interface OnboardingViewModel {
    val state: StateFlow<OnboardingContract.State>
    val effects: SharedFlow<OnboardingContract.Effect>
    fun processIntent(intent: OnboardingContract.Intent)
}
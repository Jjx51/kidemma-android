package com.kidemma.introduction.onboarding.models

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.introduction.onboarding.presentation.OnboardingViewModel

/*
 * File: OnboardingViewModelTestHarness
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class OnboardingViewModelTestSubject(
    val viewModel: OnboardingViewModel,
    val fakeUserPreferencesRepository: FakeUserPreferencesRepository
)

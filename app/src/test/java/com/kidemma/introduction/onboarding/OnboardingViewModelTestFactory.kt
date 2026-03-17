package com.kidemma.introduction.onboarding

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.introduction.onboarding.models.OnboardingViewModelTestSubject
import com.kidemma.introduction.onboarding.presentation.OnboardingViewModelImpl

/*
 * File: OnboardingViewModelInstrumentation
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */

object OnboardingViewModelTestFactory {

    fun givenAOnboardingViewModel(): OnboardingViewModelTestSubject {

        val fakeUserPreferencesRepository = FakeUserPreferencesRepository()

        val viewModel = OnboardingViewModelImpl(
            userPreferencesRepository = fakeUserPreferencesRepository
        )

        return OnboardingViewModelTestSubject(
            viewModel = viewModel,
            fakeUserPreferencesRepository = fakeUserPreferencesRepository
        )

    }
}

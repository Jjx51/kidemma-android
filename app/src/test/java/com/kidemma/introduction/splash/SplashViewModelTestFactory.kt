package com.kidemma.introduction.splash

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.introduction.splash.models.SplashViewModelTestSubject
import com.kidemma.introduction.splash.presentation.SplashViewModelImpl

/*
 * File: SplashViewModelInstrumentation
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */

object SplashViewModelTestFactory {

    suspend fun givenASplashViewModel(
        onboardingCompleted: Boolean? = null
    ): SplashViewModelTestSubject {

        val fakeUserPreferencesRepository = FakeUserPreferencesRepository()
        onboardingCompleted?.let {
            fakeUserPreferencesRepository.setOnboardingCompleted(it)
        }

        val viewModel = SplashViewModelImpl(
            userPreferencesRepository = fakeUserPreferencesRepository
        )

        return SplashViewModelTestSubject(
            viewModel = viewModel,
            fakeUserPreferencesRepository = fakeUserPreferencesRepository
        )
    }
}

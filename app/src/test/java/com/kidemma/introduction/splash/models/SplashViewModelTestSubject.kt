package com.kidemma.introduction.splash.models

import com.kidemma.common.fakes.FakeUserPreferencesRepository
import com.kidemma.introduction.splash.domain.SplashViewModel

/*
 * File: SplashViewModelHarness
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class SplashViewModelTestSubject(
    val viewModel: SplashViewModel,
    val fakeUserPreferencesRepository: FakeUserPreferencesRepository
)

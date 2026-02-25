package com.kidemma.introduction.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.interactions.UserPreferencesInteraction
import com.kidemma.common.navigation.AppRoute
import com.kidemma.extensions.isInstanceOfK
import com.kidemma.introduction.splash.presentation.SplashContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

/*
 * File: SplashViewModelTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
@ExperimentalCoroutinesApi
class SplashViewModelTest : KoinTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    // --- TEST 1: FOCUS ON EFFECTS ---
    @Test
    fun `given start countdown, when validation succeeds, then emits navigation effect`() =
        runTest {
            // --- GIVEN ---
            val harness = SplashViewModelTestFactory.givenASplashViewModel()
            val viewModel = harness.viewModel
            val userPreferencesRepository = harness.fakeUserPreferencesRepository

            // --- WHEN & THEN ---
            viewModel.effects.test {
                // 1. Trigger the action
                viewModel.processIntent(SplashContract.Intent.ValidateDestiny)

                // 2. Assert the correct navigation effect was emitted
                val effect = awaitItem()
                assertThat(effect).isInstanceOfK<SplashContract.Effect.NavigateTo>()
                val navigateEffect = effect as SplashContract.Effect.NavigateTo
                assertThat(navigateEffect.destination).isEqualTo(AppRoute.Onboarding)

                // 3. Ensure that viewmodel get the status for onboarding
                assertThat(userPreferencesRepository.interactions).containsExactly(
                    UserPreferencesInteraction.GET_ONBOARDING_STATUS
                )

                // 4. Ensure no more effects are emitted
                ensureAllEventsConsumed()
            }
        }

    @Test
    fun `given onboarding IS completed, when onboarding is completed, then navigates to Login`() =
        runTest {
            // --- GIVEN ---
            val harness =
                SplashViewModelTestFactory.givenASplashViewModel(onboardingCompleted = true)
            val viewModel = harness.viewModel
            val userPreferencesRepository = harness.fakeUserPreferencesRepository

            // --- WHEN & THEN ---
            viewModel.effects.test {
                viewModel.processIntent(SplashContract.Intent.ValidateDestiny)
                val effect = awaitItem() as SplashContract.Effect.NavigateTo
                assertThat(effect.destination).isEqualTo(AppRoute.Login)
                assertThat(userPreferencesRepository.interactions).contains(
                    UserPreferencesInteraction.GET_ONBOARDING_STATUS,
                )
            }
        }

}
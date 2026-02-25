package com.kidemma.introduction.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.navigation.AppRoute
import com.kidemma.extensions.isInstanceOfK
import com.kidemma.introduction.onboarding.presentation.OnboardingContentProvider
import com.kidemma.introduction.onboarding.presentation.OnboardingContract
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
 * File: OnboardingViewModelTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class OnboardingViewModelTest : KoinTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `initial state is correct with the first page`() = runTest {
        // --- GIVEN ---
        val harness = OnboardingViewModelTestFactory.givenAOnboardingViewModel()
        val viewModel = harness.viewModel

        // --- THEN ---
        // Assert that the initial state is exactly what we expect
        val initialState = viewModel.state.value
        assertThat(initialState.currentPageIndex).isEqualTo(0)
    }

    @Test
    fun `when OnNextClicked on first page, then state advances to second page`() = runTest {
        // --- GIVEN ---
        val harness = OnboardingViewModelTestFactory.givenAOnboardingViewModel()
        val viewModel = harness.viewModel

        // --- WHEN ---
        viewModel.processIntent(OnboardingContract.Intent.OnNextClicked)

        // --- THEN ---
        // Assert that the state has been updated to the second page
        val newState = viewModel.state.value
        assertThat(newState.currentPageIndex).isEqualTo(1)
    }


    @Test
    fun `when OnNextClicked on last page, then marks onboarding as complete and navigates to Login`() =
        runTest {
            // --- GIVEN ---
            val harness = OnboardingViewModelTestFactory.givenAOnboardingViewModel()
            val viewModel = harness.viewModel

            // We advance the ViewModel state to the last page manually
            repeat(OnboardingContentProvider.getOnboardingPages().size - 1) {
                viewModel.processIntent(OnboardingContract.Intent.OnNextClicked)
            }
            assertThat(viewModel.state.value.currentPageIndex).isEqualTo(2) // Sanity check

            // --- WHEN & THEN ---
            // We test the effect emission
            viewModel.effects.test {
                // Trigger the final "Next" click
                viewModel.processIntent(OnboardingContract.Intent.OnNextClicked)

                // Assert that the correct navigation effect was emitted
                val effect = awaitItem()
                assertThat(effect).isInstanceOfK<OnboardingContract.Effect.NavigateTo>()
                val navigateEffect = effect as OnboardingContract.Effect.NavigateTo
                assertThat(navigateEffect.screen).isEqualTo(AppRoute.Login)
            }
        }

    @Test
    fun `when OnSkipClicked, then marks onboarding as complete and navigates to Login`() = runTest {
        // --- GIVEN ---
        val harness = OnboardingViewModelTestFactory.givenAOnboardingViewModel()
        val viewModel = harness.viewModel

        // --- WHEN & THEN ---
        // We test the effect emission
        viewModel.effects.test {
            viewModel.processIntent(OnboardingContract.Intent.OnSkipClicked)

            // Assert that the correct navigation effect was emitted
            val effect = awaitItem()
            assertThat(effect).isInstanceOfK<OnboardingContract.Effect.NavigateTo>()
            val navigateEffect = effect as OnboardingContract.Effect.NavigateTo
            assertThat(navigateEffect.screen).isEqualTo(AppRoute.Login)
        }

    }

}
package com.kidemma.home_admin.tabs.others

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.navigation.AppRoute
import com.kidemma.extensions.isInstanceOfK
import com.kidemma.home_admin.tabs.others.presentation.MenuItemId
import com.kidemma.home_admin.tabs.others.presentation.OthersTabContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
 * File: OthersTabViewModelTest
 * Description: Unit tests for OthersTabViewModelImpl
 * Created by: Lino Alonso Hdez
 * Created on: 16/03/26
 * Last modified: 16/03/26
 */
class OthersTabViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    // region Initial state — admin user

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given admin user, when ViewModel is created, then state contains all items`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel(isAdminUser = true)
        val viewModel = harness.viewModel

        // --- WHEN ---
        advanceUntilIdle()

        // --- THEN ---
        assertThat(viewModel.state.value.options).hasSize(7)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given admin user, when ViewModel is created, then state includes admin panel option`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel(isAdminUser = true)
        val viewModel = harness.viewModel

        // --- WHEN ---
        advanceUntilIdle()

        // --- THEN ---
        assertThat(viewModel.state.value.options.any { it.id == MenuItemId.ADMIN_PANEL }).isTrue()
    }

    // endregion

    // region Initial state — non-admin user

    @Test
    fun `given non-admin user, when ViewModel is created, then state does not contain admin panel option`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel(isAdminUser = false)
        val viewModel = harness.viewModel

        // --- WHEN ---
        advanceUntilIdle()

        // --- THEN ---
        assertThat(viewModel.state.value.options.any { it.id == MenuItemId.ADMIN_PANEL }).isFalse()
    }

    @Test
    fun `given non-admin user, when ViewModel is created, then state contains only non-admin items`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel(isAdminUser = false)
        val viewModel = harness.viewModel

        // --- WHEN ---
        advanceUntilIdle()

        // --- THEN ---
        assertThat(viewModel.state.value.options).hasSize(5)
    }

    // region OnOptionSelected intent

    @Test
    fun `when OnOptionSelected is processed, then NavigateTo effect is emitted`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel()
        val viewModel = harness.viewModel

        // --- WHEN & THEN ---
        viewModel.effects.test {
            viewModel.processIntent(OthersTabContract.Intent.OnOptionSelected(AppRoute.AboutUs))

            val effect = awaitItem()
            assertThat(effect).isInstanceOfK<OthersTabContract.Effect.NavigateTo>()
        }
    }

    @Test
    fun `when OnOptionSelected with AboutUs route, then NavigateTo emits correct route`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel()
        val viewModel = harness.viewModel

        // --- WHEN & THEN ---
        viewModel.effects.test {
            viewModel.processIntent(OthersTabContract.Intent.OnOptionSelected(AppRoute.AboutUs))

            val effect = awaitItem() as OthersTabContract.Effect.NavigateTo
            assertThat(effect.screen).isEqualTo(AppRoute.AboutUs)
        }
    }

    @Test
    fun `when OnOptionSelected with AdminPanel route, then NavigateTo emits correct route`() = runTest {
        // --- GIVEN ---
        val harness = OthersTabViewModelTestFactory.givenAnOthersTabViewModel(isAdminUser = true)
        val viewModel = harness.viewModel

        // --- WHEN & THEN ---
        viewModel.effects.test {
            viewModel.processIntent(OthersTabContract.Intent.OnOptionSelected(AppRoute.AdminPanel))

            val effect = awaitItem() as OthersTabContract.Effect.NavigateTo
            assertThat(effect.screen).isEqualTo(AppRoute.AdminPanel)
        }
    }

}

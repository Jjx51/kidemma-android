package com.kidemma.home_admin.tabs.kids

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.utils.Gender
import com.kidemma.home_admin.tabs.kids.domain.models.KidsTabFilterResult
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

/*
 * File: KidsTabViewModelTest.kt
 * Description: Unit tests for KidsTabViewModel.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 11/03/26
 * Last modified: 14/05/26
 */

private const val ADVANCE_TIME_MS = 2001L

@OptIn(ExperimentalCoroutinesApi::class)
class KidsTabViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `when viewmodel is initialized, then content is Data`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        // WHEN
        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // THEN
        assertThat(viewModel.state.value.content)
            .isInstanceOf(KidsTabContract.KidsContentState.Data::class.java)
    }

    @Test
    fun `when OnOpenFilterDialog intent is processed, then showFilterDialog is true`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        // WHEN
        viewModel.processIntent(KidsTabContract.Intent.OnOpenFilterDialog)

        // THEN
        assertThat(viewModel.state.value.showFilterDialog).isTrue()
    }

    @Test
    fun `when OnCloseFilterDialog intent is processed, then showFilterDialog is false`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl
        viewModel.processIntent(KidsTabContract.Intent.OnOpenFilterDialog)

        // WHEN
        viewModel.processIntent(KidsTabContract.Intent.OnCloseFilterDialog)

        // THEN
        assertThat(viewModel.state.value.showFilterDialog).isFalse()
    }

    @Test
    fun `when OnToggleGridView intent is processed, then isGridView toggles`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl
        val initial = viewModel.state.value.isGridView

        // WHEN
        viewModel.processIntent(KidsTabContract.Intent.OnToggleGridView)

        // THEN
        assertThat(viewModel.state.value.isGridView).isEqualTo(!initial)
    }

    @Test
    fun `when OnApplyFilter intent is processed, then showFilterDialog is false and content is updated`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        // Ensure initial load has completed
        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // Force dialog open
        viewModel.processIntent(KidsTabContract.Intent.OnOpenFilterDialog)
        assertThat(viewModel.state.value.showFilterDialog).isTrue()

        // Create a filter that should reduce the list in most cases:
        val today = LocalDate.now()
        val filter = KidsTabFilterResult(
            minBirthday = today.minusYears(10),
            maxBirthday = today.minusMonths(12),
            gender = Gender.MALE,
        )

        // WHEN
        viewModel.processIntent(KidsTabContract.Intent.OnApplyFilter(filter))

        // THEN
        assertThat(viewModel.state.value.showFilterDialog).isFalse()
        assertThat(viewModel.state.value.content)
            .isNotInstanceOf(KidsTabContract.KidsContentState.Loading::class.java)
    }

    @Test
    fun `when OnSearchQueryChange intent is processed with match, then content is filtered Data`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // WHEN - Search for "Alice"
        viewModel.processIntent(KidsTabContract.Intent.OnSearchQueryChange("Alice"))

        // THEN
        val data = viewModel.state.value.content as KidsTabContract.KidsContentState.Data
        assertThat(data.kidDetailList).isNotEmpty()
        assertThat(data.kidDetailList.all { it.kidUiModel.name.contains("Alice", ignoreCase = true) }).isTrue()
    }

    @Test
    fun `when OnSearchQueryChange intent is processed with no match, then content is Empty`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // WHEN - Search for non-existent name
        viewModel.processIntent(KidsTabContract.Intent.OnSearchQueryChange("NonExistentName"))

        // THEN
        assertThat(viewModel.state.value.content).isInstanceOf(KidsTabContract.KidsContentState.Empty::class.java)
    }

    @Test
    fun `when both OnSearchQueryChange and OnApplyFilter are processed, then content is filtered by both`() = runTest {
        // GIVEN
        val testSubject = KidsTabViewModelTestFactory.givenAKidsViewModel()
        val viewModel = testSubject.viewModel as KidsTabViewModelImpl

        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // Filter by Female
        val femaleFilter = KidsTabFilterResult(gender = Gender.FEMALE)
        viewModel.processIntent(KidsTabContract.Intent.OnApplyFilter(femaleFilter))

        // WHEN - Search for "Grace"
        viewModel.processIntent(KidsTabContract.Intent.OnSearchQueryChange("Grace"))

        // THEN
        val data = viewModel.state.value.content as KidsTabContract.KidsContentState.Data
        assertThat(data.kidDetailList).hasSize(1)
        assertThat(data.kidDetailList.first().kidUiModel.name).isEqualTo("Grace Lee")
    }
}

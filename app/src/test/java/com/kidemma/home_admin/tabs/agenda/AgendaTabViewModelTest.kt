package com.kidemma.home_admin.tabs.agenda

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabContract
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

/*
 * File: AgendaViewModelTest
 * Description: Tests for AgendaViewModel
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

private const val ADVANCE_TIME_MS = 2001L
private const val ONE_DAY = 1L

@OptIn(ExperimentalCoroutinesApi::class)
class AgendaTabViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // Use a test dispatcher tied to runTest's scheduler so delay() is controllable.
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `when viewmodel is initialized, then content is not Loading after load completes`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel

        // WHEN
        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // THEN
        assertThat(viewModel.uiState.value.content)
            .isNotInstanceOf(AgendaTabContract.AgendaContentState.Loading::class.java)
    }

    @Test
    fun `when SelectDate intent is processed, then selectedDate updates and content is reloaded`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel

        // Ensure init load has completed
        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        val baseDate = viewModel.uiState.value.selectedDate
        val newDate = baseDate.plusDays(ONE_DAY)

        // WHEN
        viewModel.processIntent(AgendaTabContract.Intent.OnSelectDate(newDate))
        runCurrent()

        // THEN
        assertThat(viewModel.uiState.value.selectedDate).isEqualTo(newDate)
        assertThat(viewModel.uiState.value.content)
            .isInstanceOf(AgendaTabContract.AgendaContentState.Loading::class.java)

        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        // It can legitimately be Empty if there are no classes for that date.
        assertThat(viewModel.uiState.value.content)
            .isNotInstanceOf(AgendaTabContract.AgendaContentState.Loading::class.java)
    }

    @Test
    fun `when NextWeek intent is processed, then weekStart updates by one week`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val initialWeekStart = viewModel.uiState.value.weekStart

        // WHEN
        viewModel.processIntent(AgendaTabContract.Intent.OnNextWeek)

        // THEN
        assertThat(viewModel.uiState.value.weekStart).isEqualTo(initialWeekStart.plusWeeks(1))
    }

    @Test
    fun `when PreviousWeek intent is processed, then weekStart updates by minus one week`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val initialWeekStart = viewModel.uiState.value.weekStart

        // WHEN
        viewModel.processIntent(AgendaTabContract.Intent.OnPreviousWeek)

        // THEN
        assertThat(viewModel.uiState.value.weekStart).isEqualTo(initialWeekStart.minusWeeks(1))
    }

    @Test
    fun `when OpenDatePicker intent is processed, then showDatePicker is true`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel

        // WHEN
        viewModel.processIntent(AgendaTabContract.Intent.OnOpenDatePicker)

        // THEN
        assertThat(viewModel.uiState.value.showDatePicker).isTrue()
    }

    @Test
    fun `when CloseDatePicker intent is processed, then showDatePicker is false`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        viewModel.processIntent(AgendaTabContract.Intent.OnOpenDatePicker)

        // WHEN
        viewModel.processIntent(AgendaTabContract.Intent.OnCloseDatePicker)

        // THEN
        assertThat(viewModel.uiState.value.showDatePicker).isFalse()
    }

    @Test
    fun `when OnToggleExpandClass intent is processed, then expandedClassIds is updated`() = runTest {
        // GIVEN
        val testSubject = AgendaTabViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val classId = "1"

        // Ensure initial load has completed
        runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)
        runCurrent()

        val initialContent = viewModel.uiState.value.content
        if (initialContent !is AgendaTabContract.AgendaContentState.Data) {
            // If there are no classes for the current date, expand logic is a no-op; assert it doesn't crash.
            viewModel.processIntent(AgendaTabContract.Intent.OnToggleExpandClass(classId))
            assertThat(viewModel.uiState.value.content)
                .isEqualTo(initialContent)
            return@runTest
        }

        // WHEN - Expand
        viewModel.processIntent(AgendaTabContract.Intent.OnToggleExpandClass(classId))

        // THEN
        val expandedContent = viewModel.uiState.value.content as AgendaTabContract.AgendaContentState.Data
        assertThat(expandedContent.expandedClassIds).contains(classId)

        // WHEN - Collapse
        viewModel.processIntent(AgendaTabContract.Intent.OnToggleExpandClass(classId))

        // THEN
        val collapsedContent = viewModel.uiState.value.content as AgendaTabContract.AgendaContentState.Data
        assertThat(collapsedContent.expandedClassIds).doesNotContain(classId)
    }
}

package com.kidemma.home_admin.tabs.agenda

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

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
class AgendaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when viewmodel is initialized, then content is Data`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel

        // WHEN
        testDispatcher.scheduler.runCurrent()
        advanceTimeBy(ADVANCE_TIME_MS)

        // THEN
        assertThat(viewModel.uiState.value.content).isInstanceOf(AgendaContract.AgendaContentState.Data::class.java)
    }

    @Test
    fun `when SelectDate intent is processed, then selectedDate updates and content is reloaded`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        advanceTimeBy(ADVANCE_TIME_MS)
        val newDate = LocalDate.now().plusDays(ONE_DAY)

        // WHEN
        viewModel.processIntent(AgendaContract.Intent.OnSelectDate(newDate))
        testDispatcher.scheduler.runCurrent()

        // THEN (Checking loading state again)
        assertThat(viewModel.uiState.value.selectedDate).isEqualTo(newDate)
        assertThat(viewModel.uiState.value.content).isInstanceOf(AgendaContract.AgendaContentState.Loading::class.java)

        advanceTimeBy(ADVANCE_TIME_MS)
        assertThat(viewModel.uiState.value.content).isInstanceOf(AgendaContract.AgendaContentState.Data::class.java)
    }

    @Test
    fun `when NextWeek intent is processed, then weekStart updates by one week`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val initialWeekStart = viewModel.uiState.value.weekStart

        // WHEN
        viewModel.processIntent(AgendaContract.Intent.OnNextWeek)

        // THEN
        assertThat(viewModel.uiState.value.weekStart).isEqualTo(initialWeekStart.plusWeeks(1))
    }

    @Test
    fun `when PreviousWeek intent is processed, then weekStart updates by minus one week`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val initialWeekStart = viewModel.uiState.value.weekStart

        // WHEN
        viewModel.processIntent(AgendaContract.Intent.OnPreviousWeek)

        // THEN
        assertThat(viewModel.uiState.value.weekStart).isEqualTo(initialWeekStart.minusWeeks(1))
    }

    @Test
    fun `when OpenDatePicker intent is processed, then showDatePicker is true`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel

        // WHEN
        viewModel.processIntent(AgendaContract.Intent.OnOpenDatePicker)

        // THEN
        assertThat(viewModel.uiState.value.showDatePicker).isTrue()
    }

    @Test
    fun `when CloseDatePicker intent is processed, then showDatePicker is false`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        viewModel.processIntent(AgendaContract.Intent.OnOpenDatePicker)

        // WHEN
        viewModel.processIntent(AgendaContract.Intent.OnCloseDatePicker)

        // THEN
        assertThat(viewModel.uiState.value.showDatePicker).isFalse()
    }

    @Test
    fun `when OnToggleExpandClass intent is processed, then expandedClassIds is updated`() = runTest {
        // GIVEN
        val testSubject = AgendaViewModelTestFactory.givenAnAgendaViewModel()
        val viewModel = testSubject.viewModel
        val classId = "1"

        // WHEN - Expand
        viewModel.processIntent(AgendaContract.Intent.OnToggleExpandClass(classId))

        // THEN
        val content = viewModel.uiState.value.content as AgendaContract.AgendaContentState.Data
        assertThat(content.expandedClassIds).contains(classId)

        // WHEN - Collapse
        viewModel.processIntent(AgendaContract.Intent.OnToggleExpandClass(classId))

        // THEN
        val collapsedContent = viewModel.uiState.value.content as AgendaContract.AgendaContentState.Data
        assertThat(collapsedContent.expandedClassIds).doesNotContain(classId)
    }
}

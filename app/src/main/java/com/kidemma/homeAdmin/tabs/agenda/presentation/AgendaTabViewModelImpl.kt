package com.kidemma.homeAdmin.tabs.agenda.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.homeAdmin.tabs.agenda.data.AgendaTabMockProvider
import com.kidemma.homeAdmin.tabs.agenda.domain.AgendaTabViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

/*
 * File: AgendaTabViewModelImpl
 * Description: Implementation of AgendaViewModel
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

private const val LOADING_DELAY_MILLIS = 1500L
private const val DAYS_TO_ADD = 1L
private const val DAYS_TO_SUBTRACT = 1L

class AgendaTabViewModelImpl : ViewModel(), AgendaTabViewModel {

    private val _uiState = MutableStateFlow(AgendaTabContract.State())
    override val uiState: StateFlow<AgendaTabContract.State> = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<AgendaTabContract.Effect>()
    override val effects: SharedFlow<AgendaTabContract.Effect> = _effects.asSharedFlow()

    init {
        loadClasses(LocalDate.now())
    }

    override fun processIntent(intent: AgendaTabContract.Intent) {
        when (intent) {
            is AgendaTabContract.Intent.OnSelectDate -> {
                _uiState.update { it.copy(selectedDate = intent.date) }
                loadClasses(intent.date)
            }
            AgendaTabContract.Intent.OnNextWeek -> {
                val newWeekStart = _uiState.value.weekStart.plusWeeks(DAYS_TO_ADD)
                _uiState.update { it.copy(weekStart = newWeekStart) }
            }
            AgendaTabContract.Intent.OnPreviousWeek -> {
                val newWeekStart = _uiState.value.weekStart.minusWeeks(DAYS_TO_SUBTRACT)
                _uiState.update { it.copy(weekStart = newWeekStart) }
            }
            AgendaTabContract.Intent.OnOpenDatePicker -> {
                _uiState.update { it.copy(showDatePicker = true) }
            }
            AgendaTabContract.Intent.OnCloseDatePicker -> {
                _uiState.update { it.copy(showDatePicker = false) }
            }
            is AgendaTabContract.Intent.OnToggleExpandClass -> {
                _uiState.update { state ->
                    if (state.content is AgendaTabContract.AgendaContentState.Data) {
                        val currentExpandedIds = state.content.expandedClassIds
                        val newExpandedIds = if (currentExpandedIds.contains(intent.classId)) {
                            currentExpandedIds - intent.classId
                        } else {
                            currentExpandedIds + intent.classId
                        }
                        state.copy(content = state.content.copy(expandedClassIds = newExpandedIds))
                    } else {
                        state
                    }
                }
            }
        }
    }

    private fun loadClasses(date: LocalDate) {
        viewModelScope.launch {
            _uiState.update { it.copy(content = AgendaTabContract.AgendaContentState.Loading) }
            // Simulating network delay
            delay(LOADING_DELAY_MILLIS)
            val classes = AgendaTabMockProvider.getClassesForDate(date)
            val nextContent = if (classes.isEmpty()) {
                AgendaTabContract.AgendaContentState.Empty
            } else {
                AgendaTabContract.AgendaContentState.Data(classes = classes)
            }
            _uiState.update { it.copy(content = nextContent) }
        }
    }
}

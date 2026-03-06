package com.kidemma.home_admin.tabs.agenda.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.home_admin.tabs.agenda.data.AgendaMockProvider
import com.kidemma.home_admin.tabs.agenda.domain.AgendaViewModel
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
 * File: AgendaViewModelImpl
 * Description: Implementation of AgendaViewModel
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

private const val LOADING_DELAY_MILLIS = 2000L
private const val DAYS_TO_ADD = 1L
private const val DAYS_TO_SUBTRACT = 1L

class AgendaViewModelImpl : ViewModel(), AgendaViewModel {

    private val _uiState = MutableStateFlow(AgendaContract.State())
    override val uiState: StateFlow<AgendaContract.State> = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<AgendaContract.Effect>()
    override val effects: SharedFlow<AgendaContract.Effect> = _effects.asSharedFlow()

    init {
        loadClasses(LocalDate.now())
    }

    override fun processIntent(intent: AgendaContract.Intent) {
        when (intent) {
            is AgendaContract.Intent.OnSelectDate -> {
                _uiState.update { it.copy(selectedDate = intent.date) }
                loadClasses(intent.date)
            }
            AgendaContract.Intent.OnNextWeek -> {
                val newWeekStart = _uiState.value.weekStart.plusWeeks(DAYS_TO_ADD)
                _uiState.update { it.copy(weekStart = newWeekStart) }
            }
            AgendaContract.Intent.OnPreviousWeek -> {
                val newWeekStart = _uiState.value.weekStart.minusWeeks(DAYS_TO_SUBTRACT)
                _uiState.update { it.copy(weekStart = newWeekStart) }
            }
            AgendaContract.Intent.OnOpenDatePicker -> {
                _uiState.update { it.copy(showDatePicker = true) }
            }
            AgendaContract.Intent.OnCloseDatePicker -> {
                _uiState.update { it.copy(showDatePicker = false) }
            }
            is AgendaContract.Intent.OnToggleExpandClass -> {
                _uiState.update { state ->
                    if (state.content is AgendaContract.AgendaContentState.Data) {
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
            _uiState.update { it.copy(content = AgendaContract.AgendaContentState.Loading) }
            // Simulating network delay
            delay(LOADING_DELAY_MILLIS)
            val classes = AgendaMockProvider.getClassesForDate(date)
            val nextContent = if (classes.isEmpty()) {
                AgendaContract.AgendaContentState.Empty
            } else {
                AgendaContract.AgendaContentState.Data(classes = classes)
            }
            _uiState.update { it.copy(content = nextContent) }
        }
    }
}

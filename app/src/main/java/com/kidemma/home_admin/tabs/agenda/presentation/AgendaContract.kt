package com.kidemma.home_admin.tabs.agenda.presentation

import com.kidemma.home_admin.tabs.agenda.presentation.models.ClassUiModel
import java.time.LocalDate

/*
 * File: AgendaContract
 * Description: Contract for the Agenda tab defining State, Intent, and Effect
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
class AgendaContract {
    data class State(
        val selectedDate: LocalDate = LocalDate.now(),
        val weekStart: LocalDate = LocalDate.now().with(java.time.DayOfWeek.MONDAY),
        val classes: List<ClassUiModel> = emptyList(),
        val isLoading: Boolean = false,
        val showDatePicker: Boolean = false,
        val expandedClassIds: Set<String> = emptySet()
    )

    sealed interface Intent {
        data class OnSelectDate(val date: LocalDate) : Intent
        data object OnNextWeek : Intent
        data object OnPreviousWeek : Intent
        data object OnOpenDatePicker : Intent
        data object OnCloseDatePicker : Intent
        data class OnToggleExpandClass(val classId: String) : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }
}

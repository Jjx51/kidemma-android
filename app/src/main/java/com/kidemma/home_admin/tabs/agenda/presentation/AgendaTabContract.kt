package com.kidemma.home_admin.tabs.agenda.presentation

import com.kidemma.home_admin.tabs.agenda.presentation.models.ClassUiModel
import java.time.LocalDate

/*
 * File: AgendaTabContract
 * Description: Contract for the Agenda tab defining State, Intent, and Effect.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 06/03/26
 */

class AgendaTabContract {
    data class State(
        val selectedDate: LocalDate = LocalDate.now(),
        val weekStart: LocalDate = LocalDate.now().with(java.time.DayOfWeek.MONDAY),
        val content: AgendaContentState = AgendaContentState.Loading,
        val showDatePicker: Boolean = false,
    )

    sealed interface AgendaContentState {
        data object Loading : AgendaContentState
        data object Empty : AgendaContentState
        data class Data(
            val classes: List<ClassUiModel>,
            val expandedClassIds: Set<String> = emptySet()
        ) : AgendaContentState
    }

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

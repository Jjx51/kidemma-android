package com.kidemma.home_admin.tabs.agenda.domain

import com.kidemma.home_admin.tabs.agenda.presentation.AgendaContract
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: AgendaViewModel
 * Description: Interface for Agenda screen ViewModel
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
interface AgendaViewModel {
    val uiState: StateFlow<AgendaContract.State>
    val effects: SharedFlow<AgendaContract.Effect>
    fun processIntent(intent: AgendaContract.Intent)
}

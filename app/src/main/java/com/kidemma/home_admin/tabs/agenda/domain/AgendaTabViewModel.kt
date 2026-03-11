package com.kidemma.home_admin.tabs.agenda.domain

import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabContract
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: AgendaTabViewModel
 * Description: Interface for Agenda screen ViewModel
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
interface AgendaTabViewModel {
    val uiState: StateFlow<AgendaTabContract.State>
    val effects: SharedFlow<AgendaTabContract.Effect>
    fun processIntent(intent: AgendaTabContract.Intent)
}

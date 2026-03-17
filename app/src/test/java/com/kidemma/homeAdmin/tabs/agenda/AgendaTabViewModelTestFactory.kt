package com.kidemma.homeAdmin.tabs.agenda

import com.kidemma.homeAdmin.tabs.agenda.models.AgendaTabViewModelTestSubject
import com.kidemma.homeAdmin.tabs.agenda.presentation.AgendaTabViewModelImpl

/*
 * File: AgendaViewModelTestFactory
 * Description: Factory for creating AgendaViewModel test subjects
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
object AgendaTabViewModelTestFactory {
    fun givenAnAgendaViewModel(): AgendaTabViewModelTestSubject {
        return AgendaTabViewModelTestSubject(
            viewModel = AgendaTabViewModelImpl()
        )
    }
}

package com.kidemma.home_admin.tabs.agenda

import com.kidemma.home_admin.tabs.agenda.models.AgendaViewModelTestSubject
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaViewModelImpl

/*
 * File: AgendaViewModelTestFactory
 * Description: Factory for creating AgendaViewModel test subjects
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */
object AgendaViewModelTestFactory {
    fun givenAnAgendaViewModel(): AgendaViewModelTestSubject {
        return AgendaViewModelTestSubject(
            viewModel = AgendaViewModelImpl()
        )
    }
}

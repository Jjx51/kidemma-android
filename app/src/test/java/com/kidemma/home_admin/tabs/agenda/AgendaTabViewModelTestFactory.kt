package com.kidemma.home_admin.tabs.agenda

import com.kidemma.home_admin.tabs.agenda.models.AgendaTabViewModelTestSubject
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabViewModelImpl
import java.time.LocalDate

/*
 * File: AgendaViewModelTestFactory
 * Description: Factory for creating AgendaViewModel test subjects
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

private val STABLE_TEST_DATE = LocalDate.of(2026, 3, 2)

object AgendaTabViewModelTestFactory {
    fun givenAnAgendaViewModel(): AgendaTabViewModelTestSubject {
        return AgendaTabViewModelTestSubject(
            viewModel = AgendaTabViewModelImpl(initialDate = STABLE_TEST_DATE)
        )
    }
}

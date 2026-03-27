package com.kidemma.homeAdmin.di

import com.kidemma.homeAdmin.tabs.agenda.domain.AgendaTabViewModel
import com.kidemma.homeAdmin.tabs.agenda.presentation.AgendaTabViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: HomeAdminModule
 * Description: Koin module for Home Admin feature
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 10/02/26
 * Last modified: 26/02/26
 */

val adminModule = module {
    viewModelOf(::AgendaTabViewModelImpl) bind AgendaTabViewModel::class
}

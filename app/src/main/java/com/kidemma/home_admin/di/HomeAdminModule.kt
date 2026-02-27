package com.kidemma.home_admin.di

import com.kidemma.home_admin.tabs.agenda.domain.AgendaViewModel
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaViewModelImpl
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
    viewModelOf(::AgendaViewModelImpl) bind AgendaViewModel::class
}

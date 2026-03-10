package com.kidemma.home_admin.di

import com.kidemma.home_admin.tabs.kids.domain.KidsTabViewModel
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabViewModelImpl
import com.kidemma.home_admin.tabs.agenda.domain.AgendaTabViewModel
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import com.kidemma.home_admin.tabs.kids.domain.KidsViewModel
import com.kidemma.home_admin.tabs.kids.presentation.KidsViewModelImpl
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
    viewModelOf(::KidsTabViewModelImpl) bind KidsTabViewModel::class
}

package com.kidemma.home_admin.di

import com.kidemma.home_admin.tabs.kids.domain.KidsTabViewModel
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabViewModelImpl
import com.kidemma.home_admin.tabs.agenda.domain.AgendaTabViewModel
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import com.kidemma.home_admin.tabs.families.presentation.FamiliesTabViewModel
import com.kidemma.home_admin.tabs.others.presentation.OthersTabViewModel
import com.kidemma.home_admin.tabs.others.presentation.OthersTabViewModelImpl
import org.koin.dsl.module

/*
 * File: HomeAdminModule
 * Description: Koin module for Home Admin feature
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 10/02/26
 * Last modified: 12/03/26
 */

val adminModule = module {
    viewModelOf(::FamiliesTabViewModel)
    viewModelOf(::AgendaTabViewModelImpl) bind AgendaTabViewModel::class
    viewModelOf(::KidsTabViewModelImpl) bind KidsTabViewModel::class
    viewModelOf(::OthersTabViewModelImpl) bind OthersTabViewModel::class
}
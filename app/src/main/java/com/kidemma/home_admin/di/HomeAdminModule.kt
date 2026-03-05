package com.kidemma.home_admin.di

import com.kidemma.home_admin.tabs.others.presentation.OthersViewModel
import com.kidemma.home_admin.tabs.others.presentation.OthersViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: HomeAdminModule
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 10/02/26
 * Last modified: 10/02/26
 */

val adminModule = module{
    viewModelOf(::OthersViewModelImpl) bind OthersViewModel::class
}
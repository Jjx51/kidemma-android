package com.kidemma.authentication.di

import com.kidemma.authentication.login.presentation.LoginViewModel
import com.kidemma.authentication.login.presentation.LoginViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
 * File: AuthenticationModule
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
val authenticationModule = module {
    viewModelOf(::LoginViewModelImpl) bind LoginViewModel::class
}

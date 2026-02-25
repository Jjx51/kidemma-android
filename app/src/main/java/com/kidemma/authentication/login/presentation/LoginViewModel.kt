package com.kidemma.authentication.login.presentation

import com.kidemma.authentication.login.domain.model.LoginScreenUiModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: LoginViewModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
interface LoginViewModel {
    val uiData: LoginScreenUiModel
    val state: StateFlow<LoginContract.State>
    val effects: SharedFlow<LoginContract.Effect>
    fun processIntent(intent: LoginContract.Intent)
}
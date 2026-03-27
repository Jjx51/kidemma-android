package com.kidemma.authentication.login.presentation

import androidx.annotation.StringRes

/*
 * File: LoginContract
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
object LoginContract {

    data class State(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        @param:StringRes val errorMessage: Int? = null,
        val isPasswordVisible: Boolean = false,
        @param:StringRes val emailFormatError: Int? = null,
    )

    sealed interface Intent {
        data class OnEmailChange(val email: String) : Intent
        data class OnPasswordChange(val password: String) : Intent
        data object OnLoginClicked : Intent
        data object OnErrorShown : Intent
        data object OnTogglePasswordVisibility : Intent
    }

    sealed interface Effect {
        data object NavigateToAdminHome : Effect
        data object NavigateToUserHome : Effect
    }
}

package com.kidemma.authentication.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.authentication.login.domain.model.LoginScreenUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
 * File: LoginViewModelImpl
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class LoginViewModelImpl() : LoginViewModel, ViewModel() {
    override val uiData: LoginScreenUiModel = LoginContentProvider.getLoginScreenData()

    private val _state = MutableStateFlow(LoginContract.State())
    override val state: StateFlow<LoginContract.State> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginContract.Effect>()
    override val effects: SharedFlow<LoginContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = intent.email,
                    errorMessage = null,
                    emailFormatError = null
                )
            }

            is LoginContract.Intent.OnPasswordChange -> {
                _state.value = _state.value.copy(password = intent.password, errorMessage = null)
            }

            is LoginContract.Intent.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            LoginContract.Intent.OnErrorShown -> {
                _state.value = _state.value.copy(errorMessage = null)
            }

            LoginContract.Intent.OnLoginClicked -> performLogin()
        }
    }

    private fun performLogin() {
        val currentState = _state.value

        if (!isEmailFormatValid(currentState.email)) {
            _state.update { it.copy(emailFormatError = uiData.errorInvalidEmailFormat) }
            return
        }

        if (areFieldsBlank(currentState)) {
            _state.update { it.copy(errorMessage = uiData.errorBlankFields) }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    emailFormatError = null
                )
            }
            // NOTE just to emulate a call to the server
            delay(2000)

            //TODO It is pending to implement a call to the server
            if (currentState.email.contains("error")) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = uiData.errorInvalidCredentials
                    )
                }
            } else if (currentState.email.contains("admin")) {
                _effects.emit(LoginContract.Effect.NavigateToAdminHome)
            } else if (currentState.email.contains("home")) {
                _effects.emit(LoginContract.Effect.NavigateToUserHome)
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = uiData.errorGeneric
                    )
                }
            }
        }
    }

    //Replace this functions for Kidemma Validator
    private fun isEmailFormatValid(email: String): Boolean {
        return email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
    }

    private fun areFieldsBlank(state: LoginContract.State): Boolean {
        return state.email.isBlank() || state.password.isBlank()
    }
}
package com.kidemma.authentication.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.authentication.login.domain.model.LoginScreenUiModel
import com.kidemma.common.validation.KidemmaFieldState
import com.kidemma.common.validation.KidemmaValidator
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
class LoginViewModelImpl(
) : LoginViewModel, ViewModel() {
    override val uiData: LoginScreenUiModel = LoginContentProvider.getLoginScreenData()

    private val _state = MutableStateFlow(
        LoginContract.State(
            email = KidemmaFieldState(
                rules = uiData.emailTextField.validationRules
            ), password = KidemmaFieldState(
                rules = uiData.passwordTextField.validationRules
            )
        )
    )
    override val state: StateFlow<LoginContract.State> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<LoginContract.Effect>()
    override val effects: SharedFlow<LoginContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.OnEmailChange -> onEmailChange(intent.email)

            is LoginContract.Intent.OnPasswordChange -> onPasswordChanged(intent.password)

            LoginContract.Intent.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            LoginContract.Intent.OnErrorShown -> {
                _state.update { it.copy(errorMessage = null) }
            }

            LoginContract.Intent.OnLoginClicked -> performLogin()
        }
    }

    private fun onEmailChange(value: String) {
        val validationError = KidemmaValidator.validate(
            value = value, rules = state.value.email.rules
        )

        _state.update {
            it.copy(
                email = it.email.copy(
                    value = value, error = validationError
                )
            )
        }
    }

    private fun onPasswordChanged(value: String) {
        val validationError = KidemmaValidator.validate(
            value, state.value.password.rules
        )

        _state.update {
            it.copy(
                password = it.password.copy(
                    value = value, error = validationError
                )
            )
        }
    }

    private fun performLogin() {
        val currentState = _state.value

        if (!state.value.isSubmitEnabled) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                )
            }
            // NOTE just to emulate a call to the server
            delay(1500)

            //TODO It is pending to implement a call to the server
            if (currentState.email.value.contains("error")) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = uiData.errorInvalidCredentials
                    )
                }
            } else if (currentState.email.value.contains("admin")) {
                _state.update { it.copy(isLoading = false) }
                _effects.emit(LoginContract.Effect.NavigateToAdminHome)
            } else if (currentState.email.value.contains("home")) {
                _state.update { it.copy(isLoading = false) }
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

}
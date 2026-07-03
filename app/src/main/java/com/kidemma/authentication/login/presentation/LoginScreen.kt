package com.kidemma.authentication.login.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kidemma.authentication.login.domain.model.LoginScreenUiModel
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.components.KidemmaOutlinedTextField
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.VerticalSpacerMedium
import com.kidemma.common.components.VerticalSpacerXXLarge
import com.kidemma.common.components.VerticalSpacerXXXLarge
import com.kidemma.common.navigation.AppRoute
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.validation.asMessage
import org.koin.androidx.compose.koinViewModel

/*
 * File: LoginScreen
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModelImpl = koinViewModel()
){
    HandleLoginEffects(navController, viewModel)
    LoginScreenContent(navController, viewModel)
}

@Composable
private fun LoginScreenContent(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiData = viewModel.uiData
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainContent(
                state = state,
                uiData = uiData,
                onEmailChange = { viewModel.processIntent(LoginContract.Intent.OnEmailChange(it)) },
                onPasswordChange = {
                    viewModel.processIntent(
                        LoginContract.Intent.OnPasswordChange(
                            it
                        )
                    )
                },
                onLoginClicked = { viewModel.processIntent(LoginContract.Intent.OnLoginClicked) },
                onTogglePasswordVisibility = { viewModel.processIntent(LoginContract.Intent.OnTogglePasswordVisibility) }
            )
            if (state.isLoading) {
                KidemmaLoadingOverlay()
            }
            state.errorMessage?.let {
                GenericErrorMessage(
                    errorMessage = it,
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    state: LoginContract.State,
    uiData: LoginScreenUiModel,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onTogglePasswordVisibility: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(KidemmaDimens.GeneralPaddingFillMaxSize)
    ) {

        VerticalSpacerXXXLarge()

        Image(
            painter = painterResource(uiData.logo.resId),
            contentDescription = stringResource(uiData.logo.contentDescription),
            modifier = Modifier
                .size(uiData.logo.size)
                .align(Alignment.CenterHorizontally),
        )

        VerticalSpacerXXLarge()

        KidemmaOutlinedTextField(
            data = uiData.emailTextField,
            value = state.email.value,
            onValueChange = onEmailChange,
            enabled = state.isLoading.not(),
            isError = state.email.isNotValid,
            errorMessage = state.email.error?.asMessage(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
        )

        VerticalSpacerMedium()

        KidemmaOutlinedTextField(
            data = uiData.passwordTextField, value = state.password.value,
            onValueChange = onPasswordChange,
            enabled = state.isLoading.not(), isError = state.password.isNotValid, errorMessage = state.password.error?.asMessage(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onTrailingIconClick = onTogglePasswordVisibility,
            isTrailingIconActive = state.isPasswordVisible
        )

        VerticalSpacerMedium()

        KidemmaPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(uiData.buttonText), enabled = state.isSubmitEnabled,
            onClick = onLoginClicked
        )
    }
}

@Composable
private fun HandleLoginEffects(
    navController: NavController,
    viewModel: LoginViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                LoginContract.Effect.NavigateToAdminHome -> {
                    navController.navigate(AppRoute.AdminMain) {
                        popUpTo<AppRoute.Login> { inclusive = true }
                        launchSingleTop = true
                    }
                }

                LoginContract.Effect.NavigateToUserHome -> {
                    navController.navigate(AppRoute.UserMain) {
                        popUpTo<AppRoute.Login> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}

@Composable
private fun GenericErrorMessage(
    @StringRes errorMessage: Int,
    viewModel: LoginViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    LaunchedEffect(errorMessage) {
        val message = navController.context.getString(errorMessage)
        snackbarHostState.showSnackbar(message = message)
        viewModel.processIntent(LoginContract.Intent.OnErrorShown)
    }
}
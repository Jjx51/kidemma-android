package com.kidemma.introduction.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.common.data.local.UserPreferencesRepository
import com.kidemma.common.navigation.AppRoute
import com.kidemma.introduction.splash.domain.SplashViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/*
 * File: SplashViewModelImpl
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class SplashViewModelImpl(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel(), SplashViewModel {

    private val _effects = MutableSharedFlow<SplashContract.Effect>()
    override val effects: SharedFlow<SplashContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: SplashContract.Intent) {
        when(intent){
            SplashContract.Intent.ValidateDestiny -> validateDestiny()
        }
    }

    private fun validateDestiny() {
        viewModelScope.launch {
            val destination = performValidations()
            _effects.emit(SplashContract.Effect.NavigateTo(destination))
        }
    }

    private suspend fun performValidations(): AppRoute {
        // TODO: Here Implement the validations
        val isOnboardingComplete = userPreferencesRepository.isOnboardingCompleted.first()

        return if (isOnboardingComplete) {
            when {
                // !isUserLoggedIn() -> Screen.Login
                // isUserLoggedIn() && !isAdminUser() -> Screen.Home
                // isUserLoggedIn() && isAdminUser() -> Screen.AdminHome
                else -> AppRoute.Login
            }
        } else {
            AppRoute.Onboarding
        }
    }



}
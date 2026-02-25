package com.kidemma.introduction.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.common.navigation.AppRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
 * File: OnboardingViewModelImpl
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class OnboardingViewModelImpl() : ViewModel(), OnboardingViewModel {

    private val onboardingPages = OnboardingContentProvider.getOnboardingPages()

    private val _state = MutableStateFlow(
        OnboardingContract.State(
            currentPage = onboardingPages.first(),
            currentPageIndex = 0,
            totalPages = onboardingPages.size
        )
    )

    override val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingContract.Effect>()
    override val effects = _effects.asSharedFlow()

    override fun processIntent(intent: OnboardingContract.Intent) {
        when (intent) {
            is OnboardingContract.Intent.OnNextClicked -> handleNextClicked()
            is OnboardingContract.Intent.OnSkipClicked -> finishOnboarding()
        }
    }

    private fun handleNextClicked() {
        val currentIndex = _state.value.currentPageIndex
        val isLastPage = currentIndex == onboardingPages.size - 1

        if (isLastPage) {
            finishOnboarding()
        } else {
            val nextIndex = currentIndex + 1
            _state.update {
                it.copy(
                    currentPage = onboardingPages[nextIndex],
                    currentPageIndex = nextIndex
                )
            }
        }
    }

    private fun finishOnboarding() {
        viewModelScope.launch {
            //userPreferencesRepository.setOnboardingCompleted(true)
            _effects.emit(OnboardingContract.Effect.NavigateTo(AppRoute.Login))
        }
    }
}
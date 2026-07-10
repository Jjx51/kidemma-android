package com.kidemma.my_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.my_profile.data.MyProfileScreenUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class MyProfileViewModelImpl : ViewModel(), MyProfileViewModel {

    private val _isDarkThemeEnabled = MutableStateFlow(false)
    private val _state = MutableStateFlow(
        MyProfileContract.State(
            name = "Daniel Morgan López",
            phoneNumber = "55 3329 0001",
            profilePictureUrl = "https://randomuser.me/api/portraits/men/95.jpg",
        )
    )

    override val uiData: MyProfileScreenUiModel =
        MyProfileScreenUiFactory.getMyProfileScreenData(isDarkThemeEnabled = _state.value.isDarkThemeEnabled)


    override val state: StateFlow<MyProfileContract.State> =
        combine(_state, _isDarkThemeEnabled) { state, isDarkThemeEnabled ->
            state.copy(isDarkThemeEnabled = isDarkThemeEnabled)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    private val _effects = MutableSharedFlow<MyProfileContract.Effect>()
    override val effects: SharedFlow<MyProfileContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: MyProfileContract.Intent) {
        when (intent) {
            MyProfileContract.Intent.OnEditProfileClicked -> {
                _effects.tryEmit(MyProfileContract.Effect.NavigateToEditProfile)
            }

            MyProfileContract.Intent.OnLogoutClicked -> {
                _effects.tryEmit(MyProfileContract.Effect.NavigateToLogin)
            }

            MyProfileContract.Intent.OnChangeLanguageClicked -> {
                _effects.tryEmit(MyProfileContract.Effect.NavigateToLanguageSelection)
            }

            MyProfileContract.Intent.OnChangeThemeClicked -> {
                _isDarkThemeEnabled.value = !_isDarkThemeEnabled.value
            }

            MyProfileContract.Intent.OnShowTermsAndConditionsClicked -> {
                _effects.tryEmit(MyProfileContract.Effect.NavigateToTermsAndConditions)
            }

            MyProfileContract.Intent.OnShowPrivacyPolicyClicked -> {
                _effects.tryEmit(MyProfileContract.Effect.NavigateToPrivacyPolicy)
            }
        }
    }
}
package com.kidemma.home_admin.tabs.kids.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.home_admin.tabs.kids.data.KidsTabMockProvider
import com.kidemma.home_admin.tabs.kids.domain.KidsTabViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
 * File: KidsTabViewModelImpl.kt
 * Description: ViewModel implementation for the Kids tab, holding UI state and effects.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

private const val LOADING_DELAY_MILLIS = 1500L

class KidsTabViewModelImpl : ViewModel(), KidsTabViewModel {
    private val _state = MutableStateFlow(KidsTabContract.State())
    override val state: StateFlow<KidsTabContract.State> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<KidsTabContract.Effect>()
    override val effects: SharedFlow<KidsTabContract.Effect> = _effects.asSharedFlow()

    init {
        loadKids()
    }

    override fun processIntent(intent: KidsTabContract.Intent) {
        when (intent) {
            is KidsTabContract.Intent.OnToggleGridView -> {
                val currentState = _state.value
                _state.value = currentState.copy(isGridView = !currentState.isGridView)
            }

            is KidsTabContract.Intent.OnOpenFilterDialog -> {
                _state.value = _state.value.copy(showFilterDialog = true)
            }

            is KidsTabContract.Intent.OnCloseFilterDialog -> {
                _state.value = _state.value.copy(showFilterDialog = false)
            }

            is KidsTabContract.Intent.OnApplyFilter -> {
                _state.value = _state.value.copy(
                    showFilterDialog = false,
                    // content = ...
                )
            }

            is KidsTabContract.Intent.OnKidClick -> {
                // TODO: Emit navigation effect / open kid detail.
                // _effects.emit(...)
            }
        }
    }

    private fun loadKids() {
        viewModelScope.launch {
            _state.value = _state.value.copy(content = KidsTabContract.KidsContentState.Loading)

            // Simulate loading delay
            delay(LOADING_DELAY_MILLIS)

            val kidDetailList = KidsTabMockProvider.getKidsDetailList()
            _state.value = if (kidDetailList.isEmpty()) {
                _state.value.copy(content = KidsTabContract.KidsContentState.Empty)
            } else {
                _state.value.copy(content = KidsTabContract.KidsContentState.Data(kidDetailList))
            }
        }
    }
}

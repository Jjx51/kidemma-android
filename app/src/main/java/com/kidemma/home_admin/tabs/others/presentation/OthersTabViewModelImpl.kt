package com.kidemma.home_admin.tabs.others.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kidemma.common.data.local.UserPreferencesRepository
import com.kidemma.home_admin.tabs.others.presentation.OthersTabContract.Effect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
 * File: OthersTabViewModelImpl
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 16/03/26
 */
class OthersTabViewModelImpl(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel(), OthersTabViewModel {

    private val _state = MutableStateFlow(
        OthersTabContract.State(
            options = OthersTabContentProvider.getMenuOptions(isAdminUser = false)
        )
    )

    override val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<Effect>()
    override val effects: SharedFlow<Effect> = _effects.asSharedFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.isAdminUser.collect { isAdmin ->
                _state.update {
                    it.copy(options = OthersTabContentProvider.getMenuOptions(isAdminUser = isAdmin))
                }
            }
        }
    }

    override fun processIntent(intent: OthersTabContract.Intent.OnOptionSelected) {
        viewModelScope.launch {
            _effects.emit(Effect.NavigateTo(intent.appRoute))
        }
    }
}

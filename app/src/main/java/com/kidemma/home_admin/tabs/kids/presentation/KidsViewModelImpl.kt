package com.kidemma.home_admin.tabs.kids.presentation

import androidx.lifecycle.ViewModel
import com.kidemma.home_admin.tabs.kids.domain.KidsViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/*
 * File: KidsViewModelImpl.kt
 * Description: ViewModel implementation for the Kids tab, holding UI state and effects.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

class KidsViewModelImpl : ViewModel(), KidsViewModel {
    private val _state = MutableStateFlow(KidsContract.State())
    override val state: StateFlow<KidsContract.State> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<KidsContract.Effect>()
    override val effects: SharedFlow<KidsContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: KidsContract.Intent) {}
}

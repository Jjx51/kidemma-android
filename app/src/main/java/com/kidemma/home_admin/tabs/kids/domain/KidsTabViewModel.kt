package com.kidemma.home_admin.tabs.kids.domain

import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: KidsViewModel.kt
 * Description: ViewModel interface for the Kids tab (state, effects, intents).
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

interface KidsTabViewModel {
    val state: StateFlow<KidsTabContract.State>
    val effects: SharedFlow<KidsTabContract.Effect>
    fun processIntent(intent: KidsTabContract.Intent)
}

package com.kidemma.home_admin.tabs.kids.domain

import com.kidemma.home_admin.tabs.kids.presentation.KidsContract
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

interface KidsViewModel {
    val state: StateFlow<KidsContract.State>
    val effects: SharedFlow<KidsContract.Effect>
    fun processIntent(intent: KidsContract.Intent)
}

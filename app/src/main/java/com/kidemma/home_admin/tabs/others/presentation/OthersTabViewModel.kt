package com.kidemma.home_admin.tabs.others.presentation

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: OthersTabViewModel
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 12/03/26
 */
interface OthersTabViewModel {
    val state: StateFlow<OthersTabContract.State>
    val effects: SharedFlow<OthersTabContract.Effect>
    fun processIntent(intent:  OthersTabContract.Intent.OnOptionSelected)
}
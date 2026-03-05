package com.kidemma.home_admin.tabs.others.presentation

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * File: OthersViewModel
 * Description: [Short description]
 *
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 04/03/26
 */
interface OthersViewModel {
    val state: StateFlow<OthersContract.State>

     val effects: SharedFlow<OthersContract.Effect>
    fun processIntent(intent: OthersContract.Intent)
}
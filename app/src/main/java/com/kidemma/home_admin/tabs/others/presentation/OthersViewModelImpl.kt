package com.kidemma.home_admin.tabs.others.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

/*
 * File: OthersViewModelImpl
 * Description: [Short description]
 *
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 04/03/26
 */
class OthersViewModelImpl : ViewModel(), OthersViewModel {

    private val _state = MutableStateFlow(
        OthersContract.State(
            options = OthersContentProvider.options(),
            sectionTitle = OthersContentProvider.sectionTitle()
        )
    )

    override val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<OthersContract.Effect>()
    override val effects: SharedFlow<OthersContract.Effect> = _effects.asSharedFlow()

    override fun processIntent(intent: OthersContract.Intent) {
        when (intent) {
            OthersContract.Intent.AboutUsClicked -> {
                // TODO : Logic pending implementation
            }

            OthersContract.Intent.AdminPanelClicked -> {
                // TODO : Logic pending implementation
            }

            OthersContract.Intent.AlliancesClicked -> {
                // TODO : Logic pending implementation
            }

            OthersContract.Intent.ComplaintsClicked -> {
                // TODO : Logic pending implementation
            }

            OthersContract.Intent.ContactClicked -> {
                // TODO : Logic pending implementation
            }

            OthersContract.Intent.StaffClicked -> {
                // TODO : Logic pending implementation
            }
        }

    }

}

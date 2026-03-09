package com.kidemma.home_admin.tabs.kids.presentation

import com.kidemma.common.domain.models.KidUiModel

/*
 * File: KidsContract.kt
 * Description: Contract (state, intents, effects) for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

class KidsContract {
    data class State(
        val kids: List<KidUiModel> = emptyList(),
        val isLoading: Boolean = false,
        val showFilterDialog: Boolean = false,
    )

    sealed interface Intent {
        data object OnOpenFilterDialog : Intent
        data object OnCloseFilterDialog : Intent
        data object OnToggleListView : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }
}

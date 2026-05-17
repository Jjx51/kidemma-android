package com.kidemma.home_admin.tabs.kids.presentation

import com.kidemma.common.domain.models.KidDetailCardUiModel
import com.kidemma.home_admin.tabs.kids.domain.models.KidsTabFilterResult

/*
 * File: KidsTabContract.kt
 * Description: Contract (state, intents, effects) for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

class KidsTabContract {
    data class State(
        val content: KidsContentState = KidsContentState.Loading,
        val showFilterDialog: Boolean = false,
        val isGridView: Boolean = false,
    )

    sealed interface KidsContentState {
        data object Loading : KidsContentState
        data object Empty : KidsContentState
        data class Data(val kidDetailList: List<KidDetailCardUiModel>) : KidsContentState
    }

    sealed interface Intent {
        data object OnOpenFilterDialog : Intent
        data object OnCloseFilterDialog : Intent
        data object OnToggleGridView : Intent
        data class OnApplyFilter(val filter: KidsTabFilterResult) : Intent
        data class OnKidClick(val kidId: String) : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }
}

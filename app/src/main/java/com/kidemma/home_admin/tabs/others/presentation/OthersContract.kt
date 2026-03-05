package com.kidemma.home_admin.tabs.others.presentation

import com.kidemma.common.navigation.AppRoute
import com.kidemma.home_admin.tabs.others.data.OthersPageUiModel

/*
 * File: OthersContract
 * Description: [Short description]
 *
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 04/03/26
 */
object OthersContract {

    data class State(
        val options: Map<String, OthersPageUiModel>,
        val sectionTitle: Int
    )


    sealed interface Intent {
        data object AdminPanelClicked : Intent
        data object AboutUsClicked : Intent
        data object StaffClicked : Intent
        data object ContactClicked : Intent
        data object AlliancesClicked : Intent
        data object ComplaintsClicked : Intent
    }

    sealed interface Effect {
        data class NavigateTo(val screen: AppRoute) : Effect
    }

}
package com.kidemma.home_admin.tabs.others.presentation

import com.kidemma.common.navigation.AppRoute
import com.kidemma.home_admin.tabs.others.data.OthersTabScreenItem

/*
 * File: OthersTabContract
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 12/03/26
 */
object OthersTabContract {

    data class State(
        val options: List<OthersTabScreenItem>
    )


    sealed interface Intent {
        data class OnOptionSelected(val appRoute: AppRoute) : Intent
    }

    sealed interface Effect {
        data class NavigateTo(val screen: AppRoute) : Effect
    }

}
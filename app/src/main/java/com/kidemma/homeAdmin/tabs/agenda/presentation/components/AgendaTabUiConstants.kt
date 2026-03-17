package com.kidemma.homeAdmin.tabs.agenda.presentation.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
 * File: AgendaTabScreen
 * Description: Centralizes dimensions and numeric UI constants used across Agenda components
 *  to avoid scattering magic numbers in composables.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 06/03/26
 * Last modified: 06/03/26
 */

internal object AgendaTabUiConstants {

    internal object Dimens {
        val ScreenPadding: Dp = 16.dp

        val DaySelectorCardSize: Dp = 50.dp
        val DaySelectorSelectedBorderWidth: Dp = 2.dp

        val WeekRangeChipBorderWidth: Dp = 1.dp
        val WeekRangeChipPadding: Dp = 16.dp
        val WeekRangeChipIconSize: Dp = 20.dp

        val WeekNavigationIconSize: Dp = 40.dp

        val ClassTimeRowPadding: Dp = 16.dp

        val KidsRowPadding: Dp = 16.dp
        val KidAvatarSize: Dp = 48.dp
        val KidAvatarBorderWidth: Dp = 1.dp
        val KidAvatarIconSize: Dp = 32.dp

        val ClassesListContentPadding: Dp = 16.dp
        val ClassesListItemSpacing: Dp = 16.dp
    }

    internal object Numbers {
        const val DAYS_TO_ADD_FOR_THE_WEEKEND: Long = 5L
        const val DAYS_IN_SELECTOR: Int = 5

        const val MAX_KIDS_DISPLAYED: Int = 3
    }
}



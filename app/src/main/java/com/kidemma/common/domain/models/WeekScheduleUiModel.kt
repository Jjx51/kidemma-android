package com.kidemma.common.domain.models

/*
 * File: WeekScheduleUiModel.kt
 * Description: UI model representing whether there's a class on each day of the week.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

data class WeekScheduleUiModel(
    val hasClassOnMonday: Boolean = false,
    val hasClassOnTuesday: Boolean = false,
    val hasClassOnWednesday: Boolean = false,
    val hasClassOnThursday: Boolean = false,
    val hasClassOnFriday: Boolean = false,
    val hasClassOnSaturday: Boolean = false,
    val hasClassOnSunday: Boolean = false,
)

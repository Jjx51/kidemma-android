package com.kidemma.common.domain.models

import com.kidemma.common.utils.DayOfWeek

/*
 * File: WeekScheduleUiModel.kt
 * Description: UI model representing whether there's a class on each day of the week.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 22/04/26
 * Last modified: 22/04/26
 */

data class WeekScheduleUiModel(
    val schedule: Map<DayOfWeek, Boolean> = emptyMap(),
)

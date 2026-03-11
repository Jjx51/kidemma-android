package com.kidemma.common.extensions

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

/*
 * File: LocalDateExtensions.kt
 * Description: Extension utilities for java.time.LocalDate.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 10/03/26
 * Last modified: 10/03/26
 */

/**
 * Formats a [LocalDate] (assumed to be the week start) into a friendly week range label.
 *
 * Example: "10 - 16 March - 2026".
 */
internal fun LocalDate.toWeekRangeLabel(
    daysToAddForWeekend: Long,
    locale: Locale = Locale.getDefault(),
): String {
    val weekRangeEnd = plusDays(daysToAddForWeekend)
    val monthName = month.getDisplayName(TextStyle.FULL, locale)
    return "$dayOfMonth - ${weekRangeEnd.dayOfMonth} $monthName - $year"
}

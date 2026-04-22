package com.kidemma.common.utils

import android.content.Context
import com.kidemma.R

/*
 * File: AgeFormatter.kt
 * Description: Utility to format age in months into a human-readable string.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 22/04/26
 */

fun formatAgeMonths(context: Context, months: Int): String {
    val years = months / 12
    val remainingMonths = months % 12

    return when {
        years == 0 -> context.resources.getQuantityString(R.plurals.kids_age_months, remainingMonths, remainingMonths)
        remainingMonths == 0 -> context.resources.getQuantityString(R.plurals.kids_age_years, years, years)
        else -> {
            val yearsLabel = context.resources.getQuantityString(R.plurals.kids_age_years, years, years)
            val monthsLabel = context.resources.getQuantityString(R.plurals.kids_age_months, remainingMonths, remainingMonths)
            "$yearsLabel $monthsLabel"
        }
    }
}

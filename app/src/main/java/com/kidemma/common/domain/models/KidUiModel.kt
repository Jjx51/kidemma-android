package com.kidemma.common.domain.models

import android.content.Context
import com.kidemma.R
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.utils.Gender
import java.time.LocalDate
import java.time.Period

/*
 * File: KidUiModel.kt
 * Description: UI model representing a kid, including computed age description.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

data class KidUiModel(
    val id: String,
    val name: String,
    val profileImage: ImageUiModel? = null,
    val birthday: LocalDate,
    val gender: Gender = Gender.MALE
) {
    /**
     * Human-readable age based on [birthday].
     *
     * Examples:
     * - EN: "3 years 2 months", "3 months", "2 years"
     * - ES: "3 años 2 meses", "3 meses", "2 años"
     */
    fun getAgeDescription(context: Context): String = birthday.let { bday ->
        val today = LocalDate.now()
        if (bday.isAfter(today)) return@let "-"

        val period = Period.between(bday, today)
        val years = period.years
        val months = period.months

        val yearsPart = if (years > 0) {
            context.resources.getQuantityString(R.plurals.kids_age_years, years, years)
        } else ""

        val monthsPart = if (months > 0) {
            context.resources.getQuantityString(R.plurals.kids_age_months, months, months)
        } else ""

        when {
            yearsPart.isNotBlank() && monthsPart.isNotBlank() -> "$yearsPart $monthsPart"
            yearsPart.isNotBlank() -> yearsPart
            monthsPart.isNotBlank() -> monthsPart
            // less than 1 month old
            else -> context.resources.getQuantityString(R.plurals.kids_age_months, 0, 0)
        }
    }
}

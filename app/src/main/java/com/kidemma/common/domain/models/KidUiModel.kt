package com.kidemma.common.domain.models

import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.utils.Gender
import java.time.LocalDate
import java.time.Period
import java.util.Locale

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
    val ageDescription: String = birthday.let { bday ->
        val today = LocalDate.now()
        if (bday.isAfter(today)) return@let "-"

        val period = Period.between(bday, today)
        val years = period.years
        val months = period.months

        val localeLanguage = Locale.getDefault().language
        val isSpanish = localeLanguage.equals("es", ignoreCase = true)

        fun yearsPart(value: Int): String = when {
            value <= 0 -> ""
            isSpanish && value == 1 -> "1 año"
            isSpanish -> "$value años"
            !isSpanish && value == 1 -> "1 year"
            else -> "$value years"
        }

        fun monthsPart(value: Int): String = when {
            value <= 0 -> ""
            isSpanish && value == 1 -> "1 mes"
            isSpanish -> "$value meses"
            !isSpanish && value == 1 -> "1 month"
            else -> "$value months"
        }

        val y = yearsPart(years)
        val m = monthsPart(months)

        when {
            y.isNotBlank() && m.isNotBlank() -> "$y $m"
            y.isNotBlank() -> y
            m.isNotBlank() -> m
            // less than 1 month old
            else -> if (isSpanish) "0 meses" else "0 months"
        }
    }
}

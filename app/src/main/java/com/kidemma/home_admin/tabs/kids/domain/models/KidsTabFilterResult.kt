package com.kidemma.home_admin.tabs.kids.domain.models

import com.kidemma.common.utils.Gender
import java.time.LocalDate

/*
 * File: KidsFilterResult.kt
 * Description: Result model for Kids tab filters (age range and gender).
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 11/03/26
 */

data class KidsTabFilterResult(
    /**
     * Minimum allowed birthday (inclusive). Older kids have an earlier birthday.
     * Example: if max age is 6 years, max age corresponds to [minBirthday].
     */
    val minBirthday: LocalDate? = null,

    /**
     * Maximum allowed birthday (inclusive). Younger kids have a later birthday.
     * Example: if min age is 2 months, min age corresponds to [maxBirthday].
     */
    val maxBirthday: LocalDate? = null,

    /** Selected gender, or null for any. */
    val gender: Gender? = null,
)

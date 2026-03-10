package com.kidemma.home_admin.tabs.kids.domain.models

import com.kidemma.common.utils.Gender
import java.time.LocalDate

/*
 * File: KidsFilterResult.kt
 * Description: Result model for Kids tab filters (birthday and gender).
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

data class KidsTabFilterResult(
    val birthday: LocalDate,
    val gender: Gender
)

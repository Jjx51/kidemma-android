package com.kidemma.common.validation

object KidemmaValidationRules {

    val email: List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.EmailFormat
    )

    fun password(minLength: Int = 8): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.MinLength(minLength)
    )
}
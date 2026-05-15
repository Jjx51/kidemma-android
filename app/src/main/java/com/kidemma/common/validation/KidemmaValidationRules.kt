package com.kidemma.common.validation

object KidemmaValidationRules {

    fun email(maxLength: Int = 50): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.EmailFormat, KidemmaValidationRule.MaxLength(maxLength)
    )

    fun password(minLength: Int = 8, maxLength: Int = 50): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.MinLength(minLength), KidemmaValidationRule.MaxLength(maxLength), KidemmaValidationRule.NoWhitespace
    )
}
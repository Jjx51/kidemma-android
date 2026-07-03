package com.kidemma.common.validation

/*
 * File: KidemmaValidationRules.kt
 * Description: Predefined validation rule sets for common fields.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 24/02/26
 * Last modified: 19/05/26
 */
object KidemmaValidationRules {

    fun email(maxLength: Int = 50): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.EmailFormat, KidemmaValidationRule.MaxLength(maxLength)
    )

    fun password(minLength: Int = 8, maxLength: Int = 50): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Required, KidemmaValidationRule.MinLength(minLength), KidemmaValidationRule.MaxLength(maxLength), KidemmaValidationRule.NoWhitespace
    )

    fun filter(maxLength: Int = 100): List<KidemmaValidationRule> = listOf(
        KidemmaValidationRule.Optional, KidemmaValidationRule.MaxLength(maxLength), KidemmaValidationRule.NoSymbols, KidemmaValidationRule.NoNumbers
    )
}
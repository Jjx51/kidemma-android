package com.kidemma.common.validation

import com.kidemma.common.utils.RegexPatterns

/*
 * File: KidemmaValidator.kt
 * Description: Main validator logic for applying KidemmaValidationRules.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 24/02/26
 * Last modified: 19/05/26
 */
object KidemmaValidator {

    fun validate(
        value: String,
        rules: List<KidemmaValidationRule>
    ): KidemmaValidationError? {
        val optional = rules.any { it is KidemmaValidationRule.Optional }

        if (value.isBlank()) {
            return if (optional) null else KidemmaValidationError.Required
        }

        return rules.firstNotNullOfOrNull { rule ->
            when (rule) {
                KidemmaValidationRule.Required, KidemmaValidationRule.Optional -> null

                KidemmaValidationRule.EmailFormat -> validateEmailFormat(value)

                is KidemmaValidationRule.MinLength -> validateMinLength(
                    value = value,
                    length = rule.length
                )

                is KidemmaValidationRule.MaxLength -> validateMaxLength(
                    value = value,
                    length = rule.length
                )

                KidemmaValidationRule.NoSymbols -> validateNoSymbols(value)

                KidemmaValidationRule.NoNumbers -> validateNoNumbers(value)

                KidemmaValidationRule.NoWhitespace -> validateNoWhitespace(value)
            }
        }
    }

    private fun validateEmailFormat(value: String): KidemmaValidationError? {
        if (!value.matches(RegexPatterns.emailPattern)) {
            return KidemmaValidationError.InvalidEmail
        }
        return null
    }

    private fun validateMinLength(
        value: String,
        length: Int
    ): KidemmaValidationError? {
        if (value.length < length) {
            return KidemmaValidationError.MinLength(length)
        }
        return null
    }

    private fun validateMaxLength(
        value: String,
        length: Int
    ): KidemmaValidationError? {
        if (value.length > length) {
            return KidemmaValidationError.MaxLength(length)
        }
        return null
    }

    private fun validateNoSymbols(value: String): KidemmaValidationError? {
        if (value.any { !it.isLetterOrDigit() && !it.isWhitespace() }) {
            return KidemmaValidationError.NoSymbols
        }
        return null
    }

    private fun validateNoNumbers(value: String): KidemmaValidationError? {
        if (value.any { it.isDigit() }) {
            return KidemmaValidationError.NoNumbers
        }
        return null
    }

    private fun validateNoWhitespace(value: String): KidemmaValidationError? {
        if (value.contains(" ")) {
            return KidemmaValidationError.NoWhitespace
        }
        return null
    }
}
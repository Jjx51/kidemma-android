package com.kidemma.common.validation

import com.kidemma.common.utils.RegexPatterns

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


    private fun validateNoWhitespace(value: String): KidemmaValidationError? {
        if (value.contains(" ")) {
            return KidemmaValidationError.NoWhitespace
        }
        return null
    }
}
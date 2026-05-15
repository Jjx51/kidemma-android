package com.kidemma.common.validation

sealed interface KidemmaValidationRule {
    data object Required : KidemmaValidationRule
    data object Optional : KidemmaValidationRule
    data class MinLength(val length: Int) : KidemmaValidationRule
    data class MaxLength(val length: Int) : KidemmaValidationRule
    data object EmailFormat : KidemmaValidationRule
    data object NoWhitespace : KidemmaValidationRule
}
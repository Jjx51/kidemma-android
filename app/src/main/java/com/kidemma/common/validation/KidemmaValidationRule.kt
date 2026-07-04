package com.kidemma.common.validation

/*
 * File: KidemmaValidationRule.kt
 * Description: Definitions of individual validation rules.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 24/02/26
 * Last modified: 19/05/26
 */
sealed interface KidemmaValidationRule {
    data object Required : KidemmaValidationRule
    data object Optional : KidemmaValidationRule
    data class MinLength(val length: Int) : KidemmaValidationRule
    data class MaxLength(val length: Int) : KidemmaValidationRule
    data object EmailFormat : KidemmaValidationRule
    data object NoSymbols : KidemmaValidationRule
    data object NoNumbers : KidemmaValidationRule
    data object NoWhitespace : KidemmaValidationRule
}
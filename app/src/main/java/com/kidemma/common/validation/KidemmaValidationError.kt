package com.kidemma.common.validation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kidemma.R

/*
 * File: KidemmaValidationError.kt
 * Description: Sealed interface for form validation errors with template support.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 24/02/26
 * Last modified: 19/05/26
 */
sealed interface KidemmaValidationError {
    data object Required : KidemmaValidationError
    data object InvalidEmail : KidemmaValidationError
    data class MinLength(val length: Int) : KidemmaValidationError
    data class MaxLength(val length: Int) : KidemmaValidationError
    data object NoSymbols : KidemmaValidationError
    data object NoNumbers : KidemmaValidationError
    data object NoWhitespace : KidemmaValidationError
}

@Composable
fun KidemmaValidationError?.asMessage(): String? {
    val error = this ?: return null
    return when (error) {
        KidemmaValidationError.Required ->
            stringResource(R.string.error_required)

        KidemmaValidationError.InvalidEmail ->
            stringResource(R.string.error_invalid_email)

        is KidemmaValidationError.MinLength ->
            stringResource(
                R.string.error_min_length,
                error.length
            )

        is KidemmaValidationError.MaxLength ->
            stringResource(
                R.string.error_max_length,
                error.length
            )

        KidemmaValidationError.NoSymbols ->
            stringResource(R.string.error_no_symbols)

        KidemmaValidationError.NoNumbers ->
            stringResource(R.string.error_no_numbers)

        KidemmaValidationError.NoWhitespace ->
            stringResource(R.string.error_no_whitespace)
    }
}
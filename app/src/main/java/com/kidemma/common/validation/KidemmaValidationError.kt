package com.kidemma.common.validation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kidemma.R

sealed interface KidemmaValidationError {
    data object Required : KidemmaValidationError
    data object InvalidEmail : KidemmaValidationError
    data class MinLength(val length: Int) : KidemmaValidationError
}

@Composable
fun KidemmaValidationError.asMessage(): String {
    return when (this) {
        KidemmaValidationError.Required ->
            stringResource(R.string.error_required)

        KidemmaValidationError.InvalidEmail ->
            stringResource(R.string.error_invalid_email)

        is KidemmaValidationError.MinLength ->
            stringResource(
                R.string.error_min_length,
                length
            )
    }
}
package com.kidemma.common.validation

data class KidemmaFieldState(
    val value: String = "",
    val error: KidemmaValidationError? = null,
    val rules: List<KidemmaValidationRule> = emptyList()
) {
    val isValid: Boolean get() = error == null
    val isNotValid: Boolean get() = isValid.not()
}
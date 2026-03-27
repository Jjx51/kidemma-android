package com.kidemma.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
        primary = KidemmaColors.Primary,
        secondary = KidemmaColors.Secondary,
        background = KidemmaColors.Background,
        error = KidemmaColors.Error,
)

@Composable
fun KidemmaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
            colorScheme = LightColors,
            typography = KidemmaTypography,
            shapes = KidemmaShapes,
            content = content
    )
}

package com.kidemma.common.ui.theme

import androidx.compose.ui.graphics.Color

object KidemmaColors {
    // Status Colors
    val Success = Color(0xFF4CAF50)
    val Warning = Color(0xFFFF8C00)
    val Error = Color(0xFFD32F2F)
    val Info = Color(0xFF4682B4)

    // Main Colors
    val Primary = Color(0xFFFFA07A)
    val Secondary = Color(0xFFFFFFFF)
    val Background = Color(0xFFFDF9ED)
    val Text = Color(0xFF4A4A4A)
    val Title = Color(0xFFAD5645)
    val Link = Color(0xFF003366)
    val Divider = Color(0xFFD3D3D3)
    val Focus = Color(0xFF4682B4)
    val Card = Color(0xFFFFFFFF)
    val Toolbar = Color(0xFFFFFFFF)
    val Icon = Color(0xFFAD5645)
    val PlaceholderForm = Color(0xFFD3D3D3)

    // Primary Button
    val PrimaryButton = Primary
    val PrimaryButtonText = Color(0xFFFFFFFF)

    // Secondary Button
    val SecondaryButton = Secondary
    val SecondaryButtonText = Color(0xFFFFA07A)
    val SecondaryButtonBorder = Color(0xFFFFA07A)

    // Disabled Button
    val DisabledButton = Color(0xFFD3D8E1)
    val DisabledButtonText = Color(0xFF858A8E)
    val DisabledButtonBorder = Color(0xFFD3D8E1)

    // For OutlineTextField
    private val OutlinedFocusColor = Color(0xFFAD5645)
    val OutlinedFocusedBorderColor = OutlinedFocusColor
    val OutlinedFocusedLabelColor = OutlinedFocusColor
    val OutlinedFocusedIconColor = OutlinedFocusColor

    private val OutlinedUnFocusColor = Color(0xFFBF7A6A)
    val OutlinedUnfocusedBorderColor = OutlinedUnFocusColor
    val OutlinedUnfocusedLabelColor = OutlinedUnFocusColor
    val OutlinedUnfocusedIconColor = OutlinedUnFocusColor

    private val OutlinedErrorColor = Color(0xFFD32F2F)
    val OutlinedErrorBorderColor = OutlinedErrorColor
    val OutlinedErrorLabelColor = OutlinedErrorColor
    val OutlinedErrorIconColor = OutlinedErrorColor

    val AvatarBorderColor = Primary

    val BackgroundColorBottomItem = Icon
    val TextColorBottomItemSelected = Icon

    val ImageBorderStrokeColor = Icon
}

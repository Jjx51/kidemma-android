package com.kidemma.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaShapes
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography

@Composable
fun KidemmaPrimaryButton(
        text: String,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        onClick: () -> Unit,
) {
    Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = KidemmaShapes.medium,
            colors = ButtonDefaults.buttonColors(
                    containerColor = KidemmaColors.PrimaryButton,
                    contentColor = KidemmaColors.PrimaryButtonText,
                    disabledContainerColor = KidemmaColors.DisabledButton,
                    disabledContentColor = KidemmaColors.DisabledButtonText
            )
    ) {
        Text(
                text = text,
                style = KidemmaTypography.labelMedium
        )
    }
}

@Composable
fun KidemmaSecondaryButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true
) {
    OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            border = BorderStroke(
                    1.dp,
                    if (enabled) KidemmaColors.SecondaryButtonBorder else KidemmaColors.DisabledButtonBorder
            ),
            shape = KidemmaShapes.medium,
            colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = KidemmaColors.SecondaryButton,
                    contentColor = KidemmaColors.SecondaryButtonText,
                    disabledContainerColor = KidemmaColors.DisabledButton,
                    disabledContentColor = KidemmaColors.DisabledButtonText
            )
    ) {
        Text(
                text = text,
                style = KidemmaTypography.labelMedium
        )
    }
}

@Composable
fun KidemmaTertiaryButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true
) {
    TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                    contentColor = KidemmaColors.SecondaryButtonText,
                    disabledContentColor = KidemmaColors.DisabledButtonText
            )
    ) {
        Text(
                text = text,
                style = KidemmaTypography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaPrimaryButtonPreview() {
    KidemmaTheme {
        KidemmaPrimaryButton(
                text = "Primary Button",
                onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaSecondaryButtonPreview() {
    KidemmaTheme {
        KidemmaSecondaryButton(
                text = "Secondary Button",
                onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaTertiaryButtonPreview() {
    KidemmaTheme {
        KidemmaTertiaryButton(
                text = "Tertiary Button",
                onClick = {},
        )
    }
}

package com.kidemma.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography

/*
 * File: Texts
 * Description: Custom text components for Kidemma
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

@Composable
fun KidemmaHeadlineLarge(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Title
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.headlineLarge, color = color
    )
}

@Composable
fun KidemmaHeadlineMedium(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Title
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.headlineMedium, color = color
    )
}

@Composable
fun KidemmaHeadlineSmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start, color: Color = KidemmaColors.Title
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.headlineSmall, textAlign = textAlign, color = color
    )
}

@Composable
fun KidemmaBodyLarge(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.bodyLarge, color = color
    )
}

@Composable
fun KidemmaBodyMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start, fontWeight: FontWeight = FontWeight.Normal, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier,
        text = text,
        style = KidemmaTypography.bodyMedium,
        textAlign = textAlign, fontWeight = fontWeight, color = color
    )
}

@Composable
fun KidemmaBodySmall(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.bodySmall, color = color
    )
}

@Composable
fun KidemmaLabelLarge(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.labelLarge, color = color
    )
}

@Composable
fun KidemmaLabelMedium(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.labelMedium, color = color
    )
}

@Composable
fun KidemmaLabelSmall(
    modifier: Modifier = Modifier, text: String, color: Color = KidemmaColors.Text
) {
    Text(
        modifier = modifier, text = text, style = KidemmaTypography.labelSmall, color = color
    )
}

@Preview(showBackground = true)
@Composable
fun KidemmaTypographyPreview() {
    KidemmaTheme {
        androidx.compose.foundation.layout.Column {
            KidemmaHeadlineLarge(text = "Headline Large")
            KidemmaHeadlineMedium(text = "Headline Medium")
            KidemmaHeadlineSmall(text = "Headline Small")
            KidemmaBodyLarge(text = "Body Large")
            KidemmaBodyMedium(text = "Body Medium")
            KidemmaBodySmall(text = "Body Small")
            KidemmaLabelLarge(text = "Label Large")
            KidemmaLabelMedium(text = "Label Medium")
            KidemmaLabelSmall(text = "Label Small")
        }
    }
}

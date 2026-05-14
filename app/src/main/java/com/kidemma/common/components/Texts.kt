package com.kidemma.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.ui.theme.KidemmaColors
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography

@Composable
fun KidemmaHeadlineLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Title,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.headlineLarge,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaHeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Title,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.headlineMedium,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaHeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Title,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.headlineSmall,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.bodyLarge,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = KidemmaTypography.bodyMedium,
        modifier = modifier,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun KidemmaBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.bodySmall,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaLabelLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.labelLarge,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaLabelMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.labelMedium,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun KidemmaLabelSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = KidemmaColors.Text,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = KidemmaTypography.labelSmall,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Preview(showBackground = true)
@Composable
private fun KidemmaTypographyPreview() {
    KidemmaTheme {
        Column(modifier = Modifier.padding(16.dp)) {
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

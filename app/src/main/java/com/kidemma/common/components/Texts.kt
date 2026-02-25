package com.kidemma.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography

@Composable
fun KidemmaHeadlineLarge(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.headlineLarge, modifier = modifier)
}

@Composable
fun KidemmaHeadlineMedium(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.headlineMedium, modifier = modifier)
}

@Composable
fun KidemmaHeadlineSmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(text = text, style = KidemmaTypography.headlineSmall, modifier = modifier, textAlign = textAlign)
}

@Composable
fun KidemmaBodyLarge(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.bodyLarge, modifier = modifier)
}

@Composable
fun KidemmaBodyMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        style = KidemmaTypography.bodyMedium,
        modifier = modifier,
        textAlign = textAlign,
        fontWeight = fontWeight
    )
}

@Composable
fun KidemmaBodySmall(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.bodySmall, modifier = modifier)
}

@Composable
fun KidemmaLabelLarge(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.labelLarge, modifier = modifier)
}

@Composable
fun KidemmaLabelMedium(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.labelMedium, modifier = modifier)
}

@Composable
fun KidemmaLabelSmall(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.labelSmall, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun KidemmaTypographyPreview() {
    KidemmaTheme {
        androidx.compose.foundation.layout.Column {
            KidemmaHeadlineLarge("Headline Large")
            KidemmaHeadlineMedium("Headline Medium")
            KidemmaHeadlineSmall(text = "Headline Small")
            KidemmaBodyLarge("Body Large")
            KidemmaBodyMedium(text = "Body Medium")
            KidemmaBodySmall("Body Small")
            KidemmaLabelLarge("Label Large")
            KidemmaLabelMedium("Label Medium")
            KidemmaLabelSmall("Label Small")
        }
    }
}

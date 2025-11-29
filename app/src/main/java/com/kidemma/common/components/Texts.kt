package com.kidemma.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun KidemmaHeadlineSmall(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.headlineSmall, modifier = modifier)
}

@Composable
fun KidemmaBodyLarge(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.bodyLarge, modifier = modifier)
}

@Composable
fun KidemmaBodyMedium(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = KidemmaTypography.bodyMedium, modifier = modifier)
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
            KidemmaHeadlineSmall("Headline Small")
            KidemmaBodyLarge("Body Large")
            KidemmaBodyMedium("Body Medium")
            KidemmaBodySmall("Body Small")
            KidemmaLabelLarge("Label Large")
            KidemmaLabelMedium("Label Medium")
            KidemmaLabelSmall("Label Small")
        }
    }
}

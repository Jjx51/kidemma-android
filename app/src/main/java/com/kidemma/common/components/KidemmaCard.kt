package com.kidemma.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.theme.KidemmaCardShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme

@Composable
fun KidemmaCard(
    modifier: Modifier = Modifier,
    elevation: Dp = KidemmaDimens.ElevationSmall,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = KidemmaCardShapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = KidemmaColors.Card
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
fun KidemmaCardPreview() {
    KidemmaTheme {
        KidemmaCard(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompositionLocalProvider(LocalContentColor provides KidemmaColors.Title) {
                    KidemmaHeadlineLarge(text = "Card Headline")
                }
                CompositionLocalProvider(LocalContentColor provides KidemmaColors.Text) {
                    KidemmaBodyMedium(text = "This is a card body description using the custom card component.")
                }
                KidemmaPrimaryButton(text = "Click Me", onClick = {})
            }
        }
    }
}

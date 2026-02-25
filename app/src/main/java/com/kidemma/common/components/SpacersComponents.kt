package com.kidemma.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kidemma.common.ui.theme.KidemmaDimens

/*
 * File: SpacersComponents
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/11/25
 * Last modified: 24/11/25
 */
@Composable
fun VerticalSpacerExtraSmall() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerExtraSmall))
}

@Composable
fun VerticalSpacerSmall() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerSmall))
}

@Composable
fun VerticalSpacerMedium() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerMedium))
}

@Composable
fun VerticalSpacerLarge() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerLarge))
}

@Composable
fun VerticalSpacerExtraLarge() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerExtraLarge))
}

@Composable
fun VerticalSpacerXXLarge() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerXXLarge))
}

@Composable
fun VerticalSpacerXXXLarge() {
    Spacer(modifier = Modifier.height(KidemmaDimens.SpacerXXXLarge))
}

// --- Horizontal spacers ---

@Composable
fun HorizontalSpacerExtraSmall() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerExtraSmall))
}

@Composable
fun HorizontalSpacerSmall() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerSmall))
}

@Composable
fun HorizontalSpacerMedium() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerMedium))
}

@Composable
fun HorizontalSpacerLarge() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerLarge))
}

@Composable
fun HorizontalSpacerExtraLarge() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerExtraLarge))
}

@Composable
fun HorizontalSpacerXXLarge() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerXXLarge))
}

@Composable
fun HorizontalSpacerXXXLarge() {
    Spacer(modifier = Modifier.width(KidemmaDimens.SpacerXXXLarge))
}

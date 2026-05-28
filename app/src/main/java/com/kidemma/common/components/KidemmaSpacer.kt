package com.kidemma.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.kidemma.common.ui.theme.KidemmaDimens

enum class KidemmaSpacerSize {
    ExtraSmall, Small, Medium, Large, ExtraLarge, XXLarge, XXXLarge
}

@Composable
private fun KidemmaSpacerSize.toDp(): Dp = when (this) {
    KidemmaSpacerSize.ExtraSmall -> KidemmaDimens.SpacerExtraSmall
    KidemmaSpacerSize.Small -> KidemmaDimens.SpacerSmall
    KidemmaSpacerSize.Medium -> KidemmaDimens.SpacerMedium
    KidemmaSpacerSize.Large -> KidemmaDimens.SpacerLarge
    KidemmaSpacerSize.ExtraLarge -> KidemmaDimens.SpacerExtraLarge
    KidemmaSpacerSize.XXLarge -> KidemmaDimens.SpacerXXLarge
    KidemmaSpacerSize.XXXLarge -> KidemmaDimens.SpacerXXXLarge
}

@Composable
fun KidemmaVerticalSpacer(
    modifier: Modifier = Modifier,
    size: KidemmaSpacerSize = KidemmaSpacerSize.Medium
) {
    Spacer(modifier = modifier.height(size.toDp()))
}

@Composable
fun KidemmaHorizontalSpacer(
    modifier: Modifier = Modifier,
    size: KidemmaSpacerSize = KidemmaSpacerSize.Medium
) {
    Spacer(modifier = modifier.width(size.toDp()))
}

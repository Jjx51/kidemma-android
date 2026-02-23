package com.kidemma.common.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

val KidemmaShapes = Shapes(
        small = RoundedCornerShape(KidemmaDimens.CornerRadiusSmall),
        medium = RoundedCornerShape(KidemmaDimens.CornerRadiusMedium),
        large = RoundedCornerShape(KidemmaDimens.CornerRadiusLarge)
)

val KidemmaButtonShapes = Shapes(
    medium = RoundedCornerShape(KidemmaDimens.ButtonCornerRadiusMedium),
)

val KidemmaCardShapes = Shapes(
    medium = RoundedCornerShape(KidemmaDimens.CardCornerRadiusMedium),
)

package com.kidemma.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kidemma.R

val GingerBiscuit = FontFamily(
        Font(R.font.ginger_biscuit_regular)
)

val Poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_bold, FontWeight.Bold)
)

val KidemmaTypography = Typography(
        // Headlines — Ginger Biscuit
        headlineLarge = TextStyle(
                fontFamily = GingerBiscuit,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.HeadlineLarge
        ),
        headlineMedium = TextStyle(
                fontFamily = GingerBiscuit,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.HeadlineMedium
        ),
        headlineSmall = TextStyle(
                fontFamily = GingerBiscuit,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.HeadlineSmall
        ),

        // Body — Poppins
        bodyLarge = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.BodyLarge
        ),
        bodyMedium = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.BodyMedium
        ),
        bodySmall = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = KidemmaDimens.BodySmall
        ),

        // Labels — Poppins
        labelLarge = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = KidemmaDimens.LabelLarge
        ),
        labelMedium = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = KidemmaDimens.LabelMedium
        ),
        labelSmall = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = KidemmaDimens.LabelSmall
        )
)
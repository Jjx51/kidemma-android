package com.kidemma.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.enums.MessageType
import com.kidemma.common.models.CardTextElementUiModel
import com.kidemma.common.ui.theme.KidemmaButtonShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.ui.theme.KidemmaTypography
import com.kidemma.R

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
            enabled = enabled, shape = KidemmaButtonShapes.medium,
            colors = ButtonDefaults.buttonColors(
                    containerColor = KidemmaColors.PrimaryButton,
                    contentColor = KidemmaColors.PrimaryButtonText,
                    disabledContainerColor = KidemmaColors.DisabledButton,
                    disabledContentColor = KidemmaColors.DisabledButtonText
            )
    ) {
        Text(
                text = text,
                style = KidemmaTypography.labelMedium,
                color = Color.White
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
            ), shape = KidemmaButtonShapes.medium,
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

@Composable
fun KidemmaCardButton(
    cardItem: CardTextElementUiModel,
    onClick: (CardTextElementUiModel) -> Unit = { }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(cardItem) }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        cardItem.iconID?.let { icon ->
            Image(
                painter = painterResource(id = icon),
                contentDescription = stringResource(cardItem.iconDescription),
                modifier = Modifier.size(20.dp)
            )
            HorizontalSpacerMedium()
        }
        cardItem.run {
            KidemmaLabelSmall(
                text = stringResource(id = text),
                modifier = Modifier.weight(1f),
                color = displayColor,
                textAlign = TextAlign.Start
            )
            KidemmaLabelSmall(
                text = count.toString(),
                modifier = Modifier.padding(end = 8.dp),
                color = KidemmaColors.Text,
                textAlign = TextAlign.End
            )
        }
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

@Preview(showBackground = true)
@Composable
fun KidemmaCardButtonPreview() {
    KidemmaCardButton(
        cardItem = CardTextElementUiModel(
            text = R.string.testing_text,
            messageType = MessageType.DEFAULT,
            count = 1,
            iconDescription = R.string.testing_text
        ),
        onClick = { }
    )
}
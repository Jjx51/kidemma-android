package com.kidemma.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.domain.models.KidDetailCardUiModel
import com.kidemma.common.domain.models.WeekScheduleUiModel
import com.kidemma.common.ui.theme.KidemmaCardShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.common.utils.DayOfWeek
import java.time.LocalDate

/*
 * File: KidemmaCard.kt
 * Description: Reusable card composables used across the app (e.g., KidemmaCard, KidDetailCard).
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 23/02/26
 * Last modified: 22/04/26
 */

@Composable
fun KidemmaCard(
    modifier: Modifier = Modifier,
    elevation: Dp = KidemmaDimens.ElevationSmall,
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

private val KidDetailCardSectionPadding = 16.dp
private val KidDetailCardClassCircleSize = 24.dp
private val KidDetailCardClassBorderThickness = 2.dp

@Composable
fun KidDetailCard(
    kidDetailCardUiModel: KidDetailCardUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    KidemmaCard(
        modifier = modifier.clickable { onClick() },
    ) {
        Column {
            KidDetailHeader(
                name = kidDetailCardUiModel.kidUiModel.name,
                ageDescription = kidDetailCardUiModel.kidUiModel.ageDescription
            )

            HorizontalDivider(color = KidemmaColors.Divider)

            KidDetailSchedule(weekSchedule = kidDetailCardUiModel.weekScheduleUiModel)
        }
    }
}

@Composable
private fun KidDetailHeader(
    name: String,
    ageDescription: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(KidDetailCardSectionPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Todo: use the component that Laura is creating for kid profile pictures once it's ready.
        //  For now, just a placeholder box.
        Box(modifier = Modifier.size(40.dp)) {}

        HorizontalSpacerMedium()

        Column(modifier = Modifier.weight(1f)) {
            KidemmaLabelLarge(text = name)
            VerticalSpacerExtraSmall()
            Row {
                KidemmaLabelLarge(text = stringResource(R.string.kid_card_detail_label_age))
                HorizontalSpacerExtraSmall()
                KidemmaBodyMedium(text = ageDescription)
            }
        }

        Icon(
            modifier = Modifier.size(KidemmaDimens.IconSizeMedium),
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = name,
            tint = KidemmaColors.Icon,
        )
    }
}

@Composable
private fun KidDetailSchedule(
    weekSchedule: WeekScheduleUiModel,
    modifier: Modifier = Modifier,
) {
    val dayLabels = stringArrayResource(R.array.kid_card_days_of_week)
    val days = DayOfWeek.entries

    Column(modifier = modifier.padding(KidDetailCardSectionPadding)) {
        KidemmaLabelLarge(text = stringResource(R.string.kid_card_detail_label_classes))
        VerticalSpacerExtraSmall()

        Row(modifier = Modifier.fillMaxWidth()) {
            days.forEachIndexed { index, day ->
                val hasClass = weekSchedule.schedule[day] ?: false
                val label = dayLabels.getOrNull(index)?.substring(0, 1) ?: ""

                KidDetailDayItem(
                    dayLabel = label,
                    hasClass = hasClass
                )

                if (index < days.size - 1) {
                    HorizontalSpacerExtraSmall()
                }
            }
        }
    }
}

@Composable
private fun KidDetailDayItem(
    dayLabel: String,
    hasClass: Boolean,
    modifier: Modifier = Modifier,
) {
    val dayTextColor = if (hasClass) KidemmaColors.Icon else KidemmaColors.DisabledButtonText

    Box(
        modifier = modifier
            .size(KidDetailCardClassCircleSize)
            .clip(CircleShape)
            .then(
                if (hasClass) {
                    Modifier.border(
                        width = KidDetailCardClassBorderThickness,
                        color = KidemmaColors.Icon,
                        shape = CircleShape
                    )
                } else {
                    Modifier.background(KidemmaColors.DisabledButton)
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        KidemmaLabelLarge(
            text = dayLabel,
            color = dayTextColor,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
fun KidemmaCardPreview() {
    KidemmaTheme {
        KidemmaCard(modifier = Modifier.padding(16.dp)) {
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

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
fun KidDetailCardPreview() {
    KidemmaTheme {
        KidDetailCard(
            kidDetailCardUiModel = KidDetailCardUiModel(
                kidUiModel = com.kidemma.common.domain.models.KidUiModel(
                    id = "1",
                    name = "John Doe",
                    birthday = LocalDate.now().minusYears(5)
                ),
                weekScheduleUiModel = WeekScheduleUiModel(
                    schedule = mapOf(
                        DayOfWeek.MONDAY to true,
                        DayOfWeek.WEDNESDAY to true,
                        DayOfWeek.FRIDAY to true
                    )
                )
            ),
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.extensions.toWeekRangeLabel
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaHorizontalSpacer
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.components.KidemmaSpacerSize
import com.kidemma.common.components.KidemmaVerticalSpacer
import com.kidemma.common.ui.theme.KidemmaCardShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.DaySelectorCardSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.DaySelectorSelectedBorderWidth
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.ScreenPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.WeekNavigationIconSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.WeekRangeChipBorderWidth
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.WeekRangeChipIconSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.WeekRangeChipPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Numbers.DAYS_IN_SELECTOR
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Numbers.DAYS_TO_ADD_FOR_THE_WEEKEND
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabContract
import java.time.LocalDate
import java.util.Locale

/*
 * File: AgendaTabHeaderComponents
 * Description: Composable components for the header section of the Agenda tab,
 * including the week selector
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 05/03/26
 * Last modified: 06/03/26
 */

@Composable
internal fun AgendaHeader(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    weekStart: LocalDate,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(ScreenPadding),
    ) {
        AgendaWeekSelector(weekStart = weekStart, onIntent = onIntent)
        KidemmaVerticalSpacer(size = KidemmaSpacerSize.Large)
        AgendaDaySelector(selectedDate = selectedDate, weekStart = weekStart, onIntent = onIntent)
    }
}

@Composable
private fun AgendaWeekSelector(
    modifier: Modifier = Modifier,
    weekStart: LocalDate,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WeekRangeChip(weekStart = weekStart, onIntent = onIntent)
        WeekNavigationButtons(onIntent = onIntent)
    }
}

@Composable
private fun WeekRangeChip(weekStart: LocalDate, onIntent: (AgendaTabContract.Intent) -> Unit) {
    val weekRangeContentDescription = stringResource(R.string.agenda_week_range_chip_content_description)

    Row(
        modifier = Modifier
            .clip(KidemmaCardShapes.medium)
            .semantics { contentDescription = weekRangeContentDescription }
            .clickable { onIntent(AgendaTabContract.Intent.OnOpenDatePicker) }
            .background(Color.White)
            .border(WeekRangeChipBorderWidth, KidemmaColors.Icon, KidemmaCardShapes.medium)
            .padding(WeekRangeChipPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.DateRange,
            contentDescription = null,
            tint = KidemmaColors.Icon,
            modifier = Modifier.size(WeekRangeChipIconSize),
        )
        KidemmaHorizontalSpacer(size = KidemmaSpacerSize.Small)
        KidemmaLabelLarge(
            text = weekStart.toWeekRangeLabel(
                daysToAddForWeekend = DAYS_TO_ADD_FOR_THE_WEEKEND,
                locale = Locale.getDefault(),
            ),
        )
    }
}

@Composable
private fun WeekNavigationButtons(
    modifier: Modifier = Modifier,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    Row(modifier = modifier) {
        IconButton(onClick = { onIntent(AgendaTabContract.Intent.OnPreviousWeek) }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.agenda_prev_week_content_description),
                modifier = Modifier.size(WeekNavigationIconSize),
                tint = KidemmaColors.Icon,
            )
        }
        IconButton(onClick = { onIntent(AgendaTabContract.Intent.OnNextWeek) }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.agenda_next_week_content_description),
                modifier = Modifier.size(WeekNavigationIconSize),
                tint = KidemmaColors.Icon,
            )
        }
    }
}

@Composable
private fun AgendaDaySelector(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    weekStart: LocalDate,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        for (index in 0..DAYS_IN_SELECTOR) {
            val date = weekStart.plusDays(index.toLong())
            val isSelected = date == selectedDate

            KidemmaCard(
                modifier = Modifier
                    .size(DaySelectorCardSize)
                    .clickable { onIntent(AgendaTabContract.Intent.OnSelectDate(date)) }
                    .then(
                        if (isSelected) {
                            Modifier.border(DaySelectorSelectedBorderWidth, KidemmaColors.Icon, KidemmaCardShapes.medium)
                        } else {
                            Modifier
                        },
                    ),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    KidemmaLabelLarge(
                        text = date.dayOfMonth.toString(),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
private fun AgendaHeaderPreview() {
    KidemmaTheme {
        AgendaHeader(
            selectedDate = LocalDate.now(),
            weekStart = LocalDate.now().with(java.time.DayOfWeek.MONDAY),
            onIntent = {},
        )
    }
}

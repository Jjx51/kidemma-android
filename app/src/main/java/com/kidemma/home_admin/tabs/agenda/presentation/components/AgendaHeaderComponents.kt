
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
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.components.HorizontalSpacerSmall
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.components.VerticalSpacerLarge
import com.kidemma.common.ui.theme.KidemmaCardShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.DaySelectorCardSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.DaySelectorSelectedBorderWidth
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.ScreenPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.WeekNavigationIconSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.WeekRangeChipBorderWidth
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.WeekRangeChipIconSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.WeekRangeChipPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Numbers.DAYS_IN_SELECTOR
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Numbers.DAYS_TO_ADD_FOR_THE_WEEKEND
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaContract
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

/*
 * File: AgendaHeaderComponents
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
    onIntent: (AgendaContract.Intent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(ScreenPadding),
    ) {
        AgendaWeekSelector(weekStart = weekStart, onIntent = onIntent)
        VerticalSpacerLarge()
        AgendaDaySelector(selectedDate = selectedDate, weekStart = weekStart, onIntent = onIntent)
    }
}

@Composable
private fun AgendaWeekSelector(
    modifier: Modifier = Modifier,
    weekStart: LocalDate,
    onIntent: (AgendaContract.Intent) -> Unit,
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
private fun WeekRangeChip(weekStart: LocalDate, onIntent: (AgendaContract.Intent) -> Unit) {
    val weekEnd = weekStart.plusDays(DAYS_TO_ADD_FOR_THE_WEEKEND)

    Row(
        modifier = Modifier
            .clip(KidemmaCardShapes.medium)
            .clickable { onIntent(AgendaContract.Intent.OnOpenDatePicker) }
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
        HorizontalSpacerSmall()
        KidemmaLabelLarge(
            text = "${weekStart.dayOfMonth} - ${weekEnd.dayOfMonth} ${weekStart.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} - ${weekStart.year}",
        )
    }
}

@Composable
private fun WeekNavigationButtons(
    modifier: Modifier = Modifier,
    onIntent: (AgendaContract.Intent) -> Unit,
) {
    Row(modifier = modifier) {
        IconButton(onClick = { onIntent(AgendaContract.Intent.OnPreviousWeek) }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.agenda_prev_week_content_description),
                modifier = Modifier.size(WeekNavigationIconSize),
                tint = KidemmaColors.Icon,
            )
        }
        IconButton(onClick = { onIntent(AgendaContract.Intent.OnNextWeek) }) {
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
    onIntent: (AgendaContract.Intent) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        (0..DAYS_IN_SELECTOR).forEach { index ->
            val date = weekStart.plusDays(index.toLong())
            val isSelected = date == selectedDate

            KidemmaCard(
                modifier = Modifier
                    .size(DaySelectorCardSize)
                    .clickable { onIntent(AgendaContract.Intent.OnSelectDate(date)) }
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

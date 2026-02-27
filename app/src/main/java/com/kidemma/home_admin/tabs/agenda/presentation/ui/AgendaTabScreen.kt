package com.kidemma.home_admin.tabs.agenda.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.HorizontalSpacerExtraSmall
import com.kidemma.common.components.HorizontalSpacerMedium
import com.kidemma.common.components.HorizontalSpacerSmall
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.components.KidemmaLabelMedium
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTertiaryButton
import com.kidemma.common.components.VerticalSpacerLarge
import com.kidemma.common.ui.theme.KidemmaCardShapes
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.data.AgendaMockProvider
import com.kidemma.home_admin.tabs.agenda.domain.AgendaViewModel
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaContract
import com.kidemma.home_admin.tabs.agenda.presentation.models.ClassUiModel
import com.kidemma.home_admin.tabs.agenda.presentation.models.KidUiModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

/*
 * File: AgendaTabScreen
 * Description: Screen for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 26/02/26
 */

private const val DAYS_TO_ADD_FOR_WEEK_END = 5L
private const val DAYS_IN_SELECTOR = 5
private const val MAX_KIDS_DISPLAYED = 3

@Composable
fun AgendaTabScreen(
    modifier: Modifier = Modifier, viewModel: AgendaViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    AgendaTabContent(
        modifier = modifier, uiState = uiState, onIntent = viewModel::processIntent
    )
}

@Composable
fun AgendaTabContent(
    modifier: Modifier = Modifier, uiState: AgendaContract.State, onIntent: (AgendaContract.Intent) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background)
        ) {
            AgendaHeader(
                selectedDate = uiState.selectedDate, weekStart = uiState.weekStart, onIntent = onIntent
            )

            if (uiState.classes.isEmpty() && !uiState.isLoading) {
                EmptyAgendaContent()
            } else {
                AgendaContent(
                    classes = uiState.classes, expandedClassIds = uiState.expandedClassIds, onIntent = onIntent
                )
            }
        }

        if (uiState.isLoading) {
            KidemmaLoadingOverlay()
        }
    }

    if (uiState.showDatePicker) {
        AgendaDatePicker(onDateSelected = {
            onIntent(AgendaContract.Intent.OnSelectDate(it))
            onIntent(AgendaContract.Intent.OnCloseDatePicker)
        }, onDismiss = { onIntent(AgendaContract.Intent.OnCloseDatePicker) })
    }
}

@Composable
fun AgendaHeader(
    modifier: Modifier = Modifier, selectedDate: LocalDate, weekStart: LocalDate, onIntent: (AgendaContract.Intent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AgendaWeekSelector(
            weekStart = weekStart, onIntent = onIntent
        )

        VerticalSpacerLarge()

        AgendaDaySelector(
            selectedDate = selectedDate, weekStart = weekStart, onIntent = onIntent
        )
    }
}

@Composable
fun AgendaWeekSelector(
    modifier: Modifier = Modifier, weekStart: LocalDate, onIntent: (AgendaContract.Intent) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
    ) {
        // Week Range Selector
        val weekEnd = weekStart.plusDays(DAYS_TO_ADD_FOR_WEEK_END)
        Row(modifier = Modifier
            .clip(KidemmaCardShapes.medium)
            .clickable { onIntent(AgendaContract.Intent.OnOpenDatePicker) }
            .background(Color.White)
            .border(1.dp, KidemmaColors.Icon, KidemmaCardShapes.medium)
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.DateRange, contentDescription = null, tint = KidemmaColors.Icon, modifier = Modifier.size(20.dp)
            )
            HorizontalSpacerSmall()
            KidemmaLabelLarge(
                text = "${weekStart.dayOfMonth} - ${weekEnd.dayOfMonth} ${weekStart.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} - ${weekStart.year}",
            )
        }

        // Navigation Arrows
        Row {
            IconButton(onClick = { onIntent(AgendaContract.Intent.OnPreviousWeek) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.agenda_prev_week_content_description),
                    modifier = Modifier.size(40.dp),
                    tint = KidemmaColors.Icon
                )
            }
            IconButton(onClick = { onIntent(AgendaContract.Intent.OnNextWeek) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.agenda_next_week_content_description),
                    modifier = Modifier.size(40.dp),
                    tint = KidemmaColors.Icon
                )
            }
        }
    }
}

@Composable
fun AgendaDaySelector(
    modifier: Modifier = Modifier, selectedDate: LocalDate, weekStart: LocalDate, onIntent: (AgendaContract.Intent) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (0..DAYS_IN_SELECTOR).forEach { index ->
            val date = weekStart.plusDays(index.toLong())
            val isSelected = date == selectedDate

            KidemmaCard(modifier = Modifier
                .size(50.dp)
                .clickable { onIntent(AgendaContract.Intent.OnSelectDate(date)) }
                .then(
                    if (isSelected) Modifier.border(
                        2.dp, KidemmaColors.Icon, KidemmaCardShapes.medium
                    ) else Modifier
                )) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    KidemmaLabelLarge(
                        text = date.dayOfMonth.toString(), modifier = Modifier.wrapContentSize()
                    )
                }
            }
        }
    }
}

@Composable
fun AgendaContent(
    modifier: Modifier = Modifier, classes: List<ClassUiModel>, expandedClassIds: Set<String>, onIntent: (AgendaContract.Intent) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(classes) { classItem ->
            ClassCard(
                classItem = classItem, isExpanded = expandedClassIds.contains(classItem.id), onToggleExpand = { onIntent(AgendaContract.Intent.OnToggleExpandClass(classItem.id)) })
        }
    }
}

@Composable
fun ClassCard(
    modifier: Modifier = Modifier, classItem: ClassUiModel, isExpanded: Boolean, onToggleExpand: () -> Unit
) {
    KidemmaCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // Header Hour
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                KidemmaLabelMedium(
                    text = stringResource(R.string.agenda_class_time_label)
                )
                HorizontalSpacerExtraSmall()
                KidemmaBodyMedium(
                    text = classItem.timeDescription
                )
            }

            HorizontalDivider(color = KidemmaColors.Divider)

            // Kids List
            val kidsToDisplay = if (classItem.kids.size > MAX_KIDS_DISPLAYED && !isExpanded) {
                classItem.kids.take(MAX_KIDS_DISPLAYED)
            } else {
                classItem.kids
            }

            kidsToDisplay.forEach { kid ->
                KidItem(kid = kid)
                HorizontalDivider(color = KidemmaColors.Divider)
            }

            if (classItem.kids.size > MAX_KIDS_DISPLAYED) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), contentAlignment = Alignment.Center
                ) {
                    KidemmaTertiaryButton(
                        text = if (isExpanded) stringResource(R.string.agenda_collapse) else stringResource(R.string.agenda_see_more), onClick = onToggleExpand
                    )
                }
            }
        }
    }
}

@Composable
fun KidItem(
    modifier: Modifier = Modifier, kid: KidUiModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        // Mock Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.dp, KidemmaColors.Primary, CircleShape), contentAlignment = Alignment.Center
        ) {
            // Using a default icon for avatar
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Provisional
                contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.Unspecified
            )
        }

        HorizontalSpacerMedium()

        Column {
            KidemmaLabelMedium(
                text = kid.name
            )
            Row {
                KidemmaBodyMedium(text = stringResource(R.string.agenda_kid_age_label))
                HorizontalSpacerExtraSmall()
                KidemmaBodyMedium(text = kid.ageDescription)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaDatePicker(
    modifier: Modifier = Modifier, onDateSelected: (LocalDate) -> Unit, onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        modifier = modifier, onDismissRequest = onDismiss, confirmButton = {
            KidemmaPrimaryButton(
                text = stringResource(R.string.agenda_date_picker_confirm), onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        onDateSelected(date)
                    }
                })
        }) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun EmptyAgendaContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        KidemmaBodyMedium(text = stringResource(R.string.agenda_empty_state_message))
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaTabScreenPreview() {
    KidemmaTheme {
        AgendaTabContent(
            uiState = AgendaContract.State(
                classes = AgendaMockProvider.getClassesForDate(LocalDate.now())
            ), onIntent = {})
    }
}

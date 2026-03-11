package com.kidemma.home_admin.tabs.agenda.presentation.ui

import AgendaDatePickerDialog
import AgendaHeader
import EmptyClassList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.data.AgendaTabMockProvider
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabContract
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaTabViewModelImpl
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaClassList
import org.koin.androidx.compose.koinViewModel
import java.time.DayOfWeek
import java.time.LocalDate

/*
 * File: AgendaTabScreen
 * Description: Screen for the Agenda tab
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 26/02/26
 * Last modified: 06/03/26
 */

@Composable
fun AgendaTabScreen(
    modifier: Modifier = Modifier,
    viewModel: AgendaTabViewModelImpl = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    AgendaTabContent(
        modifier = modifier,
        uiState = uiState,
        onIntent = viewModel::processIntent,
    )
}

@Composable
private fun AgendaTabContent(
    modifier: Modifier = Modifier,
    uiState: AgendaTabContract.State,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background),
        ) {
            AgendaHeader(
                selectedDate = uiState.selectedDate,
                weekStart = uiState.weekStart,
                onIntent = onIntent,
            )

            AgendaTabBody(
                content = uiState.content,
                onIntent = onIntent,
            )
        }

        // The loading overlay is placed at the end of the Box so it appears above all other content when visible.
        if (uiState.content is AgendaTabContract.AgendaContentState.Loading) {
            KidemmaLoadingOverlay()
        }
    }

    if (uiState.showDatePicker) {
        AgendaDatePickerDialog(
            onDateSelected = { selectedDate ->
                onIntent(AgendaTabContract.Intent.OnSelectDate(selectedDate))
                onIntent(AgendaTabContract.Intent.OnCloseDatePicker)
            },
            onDismiss = { onIntent(AgendaTabContract.Intent.OnCloseDatePicker) },
        )
    }
}

@Composable
private fun AgendaTabBody(
    content: AgendaTabContract.AgendaContentState,
    onIntent: (AgendaTabContract.Intent) -> Unit,
) {
    if (content is AgendaTabContract.AgendaContentState.Empty) {
        EmptyClassList()
    } else if (content is AgendaTabContract.AgendaContentState.Data) {
        AgendaClassList(
            classes = content.classes,
            expandedClassIds = content.expandedClassIds,
            onToggleExpanded = { classId -> onIntent(AgendaTabContract.Intent.OnToggleExpandClass(classId)) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaTabScreenPreview() {
    KidemmaTheme {
        val weekStart = LocalDate.now().with(DayOfWeek.MONDAY)
        AgendaTabContent(
            uiState = AgendaTabContract.State(
                selectedDate = weekStart,
                weekStart = weekStart,
                content = AgendaTabContract.AgendaContentState.Data(
                    classes = AgendaTabMockProvider.getClassesForDate(weekStart),
                    expandedClassIds = setOf("1"),
                ),
                showDatePicker = false,
            ),
            onIntent = {},
        )
    }
}

// Kept to avoid imports being optimized away in a future edit; also used by preview/state.
@Suppress("unused")
private fun _keepLocalDateReference(date: LocalDate = LocalDate.now()) = date

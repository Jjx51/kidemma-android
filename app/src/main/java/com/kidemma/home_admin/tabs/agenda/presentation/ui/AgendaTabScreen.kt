package com.kidemma.home_admin.tabs.agenda.presentation.ui

import AgendaDatePickerDialog
import AgendaHeader
import EmptyAgendaContent
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
import com.kidemma.home_admin.tabs.agenda.data.AgendaMockProvider
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaContract
import com.kidemma.home_admin.tabs.agenda.presentation.AgendaViewModelImpl
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaClassesList
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
    viewModel: AgendaViewModelImpl = koinViewModel(),
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
    uiState: AgendaContract.State,
    onIntent: (AgendaContract.Intent) -> Unit,
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

            when (val content = uiState.content) {
                AgendaContract.AgendaContentState.Loading -> {
                    // Handled by the overlay below to ensure it's on top of everything
                }

                AgendaContract.AgendaContentState.Empty -> EmptyAgendaContent()

                is AgendaContract.AgendaContentState.Data -> {
                    AgendaClassesList(
                        classes = content.classes,
                        expandedClassIds = content.expandedClassIds,
                        onToggleExpanded = { classId ->
                            onIntent(AgendaContract.Intent.OnToggleExpandClass(classId))
                        },
                    )
                }
            }
        }

        if (uiState.content is AgendaContract.AgendaContentState.Loading) {
            KidemmaLoadingOverlay()
        }
    }

    if (uiState.showDatePicker) {
        AgendaDatePickerDialog(
            onDateSelected = {
                onIntent(AgendaContract.Intent.OnSelectDate(it))
                onIntent(AgendaContract.Intent.OnCloseDatePicker)
            },
            onDismiss = { onIntent(AgendaContract.Intent.OnCloseDatePicker) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AgendaTabScreenPreview() {
    KidemmaTheme {
        val weekStart = LocalDate.now().with(DayOfWeek.MONDAY)
        AgendaTabContent(
            uiState = AgendaContract.State(
                selectedDate = weekStart,
                weekStart = weekStart,
                content = AgendaContract.AgendaContentState.Data(
                    classes = AgendaMockProvider.getClassesForDate(weekStart),
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

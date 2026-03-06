package com.kidemma.home_admin.tabs.agenda.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.ui.theme.KidemmaTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AgendaDatePickerDialog(
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            KidemmaPrimaryButton(
                text = stringResource(R.string.agenda_date_picker_confirm),
                onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        onDateSelected(date)
                    }
                },
            )
        },
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
internal fun EmptyAgendaContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        KidemmaBodyMedium(text = stringResource(R.string.agenda_empty_state_message))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
private fun EmptyAgendaContentPreview() {
    KidemmaTheme {
        EmptyAgendaContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
private fun AgendaDatePickerDialogPreview() {
    KidemmaTheme {
        AgendaDatePickerDialog(
            onDateSelected = {},
            onDismiss = {},
        )
    }
}

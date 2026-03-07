package com.kidemma.home_admin.tabs.agenda.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.ClassTimeRowPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.ClassesListContentPadding
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaUiConstants.Dimens.ClassesListItemSpacing
import com.kidemma.R
import com.kidemma.common.components.HorizontalSpacerExtraSmall
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaLabelMedium
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.data.AgendaMockProvider
import java.time.LocalDate
import com.kidemma.home_admin.tabs.agenda.presentation.models.ClassUiModel

/*
 * File: AgendaContentComponents
 * Description: Composables for the Agenda tab content (classes list + class cards).
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 05/03/26
 * Last modified: 06/03/26
 */

@Composable
internal fun AgendaClassesList(
    modifier: Modifier = Modifier,
    classes: List<ClassUiModel>,
    expandedClassIds: Set<String>,
    onToggleExpanded: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(ClassesListContentPadding),
        verticalArrangement = Arrangement.spacedBy(ClassesListItemSpacing),
    ) {
        items(classes) { classItem ->
            ClassCard(
                classItem = classItem,
                isExpanded = expandedClassIds.contains(classItem.id),
                onToggleExpand = { onToggleExpanded(classItem.id) },
            )
        }
    }
}

@Composable
private fun ClassCard(
    modifier: Modifier = Modifier,
    classItem: ClassUiModel,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
) {
    // Decided to keep logic here for simplicity, but if it grows more complex we can move it to a ViewModel or helper function
    val maxKids = AgendaUiConstants.Numbers.MAX_KIDS_DISPLAYED
    val hasMoreKids = classItem.kids.size > maxKids
    val kidsToDisplay = if (!isExpanded && hasMoreKids) classItem.kids.take(maxKids) else classItem.kids

    KidemmaCard(modifier = modifier.fillMaxWidth()) {
        Column {
            ClassTimeRow(timeDescription = classItem.timeDescription)
            HorizontalDivider(color = KidemmaColors.Divider)
            KidsSection(
                kidsToDisplay = kidsToDisplay,
                hasMoreKids = hasMoreKids,
                isExpanded = isExpanded,
                onToggleExpand = onToggleExpand,
            )
        }
    }
}

@Composable
private fun ClassTimeRow(timeDescription: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ClassTimeRowPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KidemmaLabelMedium(text = stringResource(R.string.agenda_class_time_label))
        HorizontalSpacerExtraSmall()
        KidemmaBodyMedium(text = timeDescription)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFDF9ED)
@Composable
private fun AgendaClassesListPreview() {
    KidemmaTheme {
        val selectedDate = LocalDate.now().with(java.time.DayOfWeek.MONDAY)
        AgendaClassesList(
            classes = AgendaMockProvider.getClassesForDate(selectedDate),
            expandedClassIds = setOf("1"),
            onToggleExpanded = {},
        )
    }
}

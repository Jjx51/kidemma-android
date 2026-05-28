package com.kidemma.home_admin.tabs.agenda.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.R
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaHorizontalSpacer
import com.kidemma.common.components.KidemmaLabelMedium
import com.kidemma.common.components.KidemmaSpacerSize
import com.kidemma.common.components.KidemmaTertiaryButton
import com.kidemma.common.components.KidemmaVerticalSpacer
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.KidAvatarBorderWidth
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.KidAvatarIconSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.KidAvatarSize
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.KidsRowPadding
import com.kidemma.home_admin.tabs.agenda.presentation.models.KidUiModel

/*
 * File: AgendaTabKidComponents
 * Description: Composable components related to displaying kids in the Agenda tab,
 * including the list of kids for a class and individual kid rows.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 05/03/26
 * Last modified: 06/03/26
 */

@Composable
internal fun KidsSection(
    kidsToDisplay: List<KidUiModel>,
    hasMoreKids: Boolean,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
) {
    Column {
        kidsToDisplay.forEachIndexed { index, kid ->
            KidRow(kid = kid)
            if (index < kidsToDisplay.lastIndex) {
                HorizontalDivider(color = KidemmaColors.Divider)
            }
        }

        if (hasMoreKids) {
            KidemmaVerticalSpacer(size = KidemmaSpacerSize.Small)
            KidemmaTertiaryButton(
                text = if (isExpanded) {
                    stringResource(R.string.agenda_collapse)
                } else {
                    stringResource(R.string.agenda_see_more)
                },
                onClick = onToggleExpand,
            )
        }
    }
}

@Composable
private fun KidRow(kid: KidUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(KidsRowPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // TODO: Replace with KidemmaAvatarChip
        Box(
            modifier = Modifier
                .size(KidAvatarSize)
                .clip(CircleShape)
                .border(KidAvatarBorderWidth, KidemmaColors.Primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(KidAvatarIconSize),
                tint = Color.Unspecified,
            )
        }

        KidemmaHorizontalSpacer()
        KidDetails(kid = kid)
    }
}

@Composable
private fun KidDetails(kid: KidUiModel) {
    Column {
        KidemmaLabelMedium(text = kid.name)
        Row {
            KidemmaBodyMedium(text = stringResource(R.string.agenda_kid_age_label))
            KidemmaHorizontalSpacer(size = KidemmaSpacerSize.ExtraSmall)
            KidemmaBodyMedium(text = kid.ageDescription)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun KidsSectionCollapsedPreview() {
    KidemmaTheme {
        KidsSection(
            kidsToDisplay = listOf(
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses"),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses"),
                KidUiModel("3", "José Carrillo", "1 año 6 meses"),
                KidUiModel("4", "Mateo Mendoza", "7 meses"),
            ),
            hasMoreKids = true,
            isExpanded = false,
            onToggleExpand = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun KidsSectionExpandedPreview() {
    KidemmaTheme {
        KidsSection(
            kidsToDisplay = listOf(
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses"),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses"),
                KidUiModel("3", "José Carrillo", "1 año 6 meses"),
                KidUiModel("4", "Mateo Mendoza", "7 meses"),
            ),
            hasMoreKids = true,
            isExpanded = true,
            onToggleExpand = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun KidsSectionShortListPreview() {
    KidemmaTheme {
        KidsSection(
            kidsToDisplay = listOf(
                KidUiModel("1", "Luna Lunaria", "8 meses"),
                KidUiModel("2", "Santiago Cruz", "2 años"),
            ),
            hasMoreKids = false,
            isExpanded = false,
            onToggleExpand = {},
        )
    }
}

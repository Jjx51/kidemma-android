package com.kidemma.home_admin.tabs.agenda.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.kidemma.common.utils.Gender
import com.kidemma.common.utils.defaultAvatarResId
import com.kidemma.home_admin.tabs.agenda.presentation.components.AgendaTabUiConstants.Dimens.KidsRowPadding
import com.kidemma.home_admin.tabs.agenda.presentation.models.KidUiModel

/*
 * File: AgendaTabKidComponents
 * Description: Composable components related to displaying kids in the Agenda tab,
 * including the list of kids for a class and individual kid rows.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 05/03/26
 * Last modified: 28/05/26
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
        val errorImage = kid.gender.defaultAvatarResId

        KidemmaAvatar(
            memberImage = kid.image?.resId ?: errorImage, memberErrorImage = errorImage
        )

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
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses", gender = Gender.MALE),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses", gender = Gender.FEMALE),
                KidUiModel("3", "José Carrillo", "1 año 6 meses", gender = Gender.MALE),
                KidUiModel("4", "Mateo Mendoza", "7 meses", gender = Gender.MALE),
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
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses", gender = Gender.MALE),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses", gender = Gender.FEMALE),
                KidUiModel("3", "José Carrillo", "1 año 6 meses", gender = Gender.MALE),
                KidUiModel("4", "Mateo Mendoza", "7 meses", gender = Gender.MALE),
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
                KidUiModel("1", "Luna Lunaria", "8 meses", gender = Gender.FEMALE),
                KidUiModel("2", "Santiago Cruz", "2 años", gender = Gender.MALE),
            ),
            hasMoreKids = false,
            isExpanded = false,
            onToggleExpand = {},
        )
    }
}

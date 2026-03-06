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
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.HorizontalSpacerExtraSmall
import com.kidemma.common.components.HorizontalSpacerMedium
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaLabelMedium
import com.kidemma.common.components.KidemmaTertiaryButton
import com.kidemma.common.components.VerticalSpacerSmall
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.agenda.presentation.models.KidUiModel

private const val MAX_KIDS_DISPLAYED = 3

@Composable
internal fun KidsSection(
    kids: List<KidUiModel>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
) {
    val kidsToDisplay = if (kids.size > MAX_KIDS_DISPLAYED && !isExpanded) {
        kids.take(MAX_KIDS_DISPLAYED)
    } else {
        kids
    }

    Column {
        kidsToDisplay.forEachIndexed { index, kid ->
            KidRow(kid = kid)
            if (index < kidsToDisplay.lastIndex) {
                HorizontalDivider(color = KidemmaColors.Divider)
            }
        }

        if (kids.size > MAX_KIDS_DISPLAYED) {
            VerticalSpacerSmall()
            KidemmaTertiaryButton(
                text = if (isExpanded) stringResource(R.string.agenda_collapse) else stringResource(R.string.agenda_see_more),
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
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // TODO: Replace with KidemmaAvatarChip
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.dp, KidemmaColors.Primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            // Using a default icon for avatar
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified,
            )
        }

        HorizontalSpacerMedium()
        KidDetails(kid = kid)
    }
}

@Composable
private fun KidDetails(kid: KidUiModel) {
    Column {
        KidemmaLabelMedium(text = kid.name)
        Row {
            KidemmaBodyMedium(text = stringResource(R.string.agenda_kid_age_label))
            HorizontalSpacerExtraSmall()
            KidemmaBodyMedium(text = kid.ageDescription)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun KidsSectionCollapsedPreview() {
    KidemmaTheme {
        KidsSection(
            kids = listOf(
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses"),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses"),
                KidUiModel("3", "José Carrillo", "1 año 6 meses"),
                KidUiModel("4", "Mateo Mendoza", "7 meses"),
            ),
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
            kids = listOf(
                KidUiModel("1", "Alejandro Ávila", "1 año 7 meses"),
                KidUiModel("2", "Brenda Barrera", "1 año 5 meses"),
                KidUiModel("3", "José Carrillo", "1 año 6 meses"),
                KidUiModel("4", "Mateo Mendoza", "7 meses"),
            ),
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
            kids = listOf(
                KidUiModel("1", "Luna Lunaria", "8 meses"),
                KidUiModel("2", "Santiago Cruz", "2 años"),
            ),
            isExpanded = false,
            onToggleExpand = {},
        )
    }
}

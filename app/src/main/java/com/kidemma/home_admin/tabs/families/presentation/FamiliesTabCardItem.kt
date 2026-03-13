package com.kidemma.home_admin.tabs.families.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.authentication.domain.model.FamilyMemberUiModel
import com.kidemma.common.components.KidemmaAvatarChip
import com.kidemma.common.components.KidemmaBodyLarge
import com.kidemma.common.components.KidemmaCard
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.home_admin.tabs.families.domain.FamiliesTabMockProvider

/*
 * File: FamiliesTabCardItem
 * Description: card item for each family
 *
 * Created by: Laura Zermeño Pichardo
 * Created on: 26/02/26
 * Last modified: 12/03/26
 */
@Composable
fun FamiliesTabCardItem(
    familyName: String,
    familyNickname: String,
    familyMembers: List<FamilyMemberUiModel> = emptyList(),
    onNavigateToDetail: () -> Unit
) {
    val icArrowRight = painterResource(R.drawable.ic_arrow_right)
    KidemmaCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                KidemmaLabelLarge(
                    text = stringResource(
                        R.string.families_tab_card_item_family,
                        familyName
                    )
                )
                KidemmaBodyLarge(
                    text = stringResource(
                        R.string.families_tab_card_item_nickname,
                        familyNickname
                    )
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(familyMembers) { member ->
                        KidemmaAvatarChip(
                            memberImage = member.image,
                            memberErrorImage = member.imageError
                        )
                    }
                }
            }
            Icon(
                painter = icArrowRight,
                contentDescription = stringResource(R.string.families_tab_card_item_view_family_details),
                tint = KidemmaColors.Icon,
                modifier = Modifier.size(30.dp).clickable{onNavigateToDetail()}
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FamiliesTabCardItemPreview() {
    FamiliesTabCardItem(
        familyName = "Perez Gonzalez",
        familyNickname = "Los Perez",
        familyMembers = FamiliesTabMockProvider.familyList.get(2).members
    ) { }
}

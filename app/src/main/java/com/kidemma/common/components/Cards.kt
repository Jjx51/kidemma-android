package com.kidemma.common.components

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
import com.kidemma.common.ui.theme.KidemmaColors

@Composable
fun KidemmaFamilyCardItem(
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
            Column(modifier = Modifier.weight(8f), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                KidemmaLabelLarge(text = stringResource(R.string.family_screen_family, familyName))
                KidemmaBodyLarge(text = stringResource(R.string.family_screen_nickname, familyNickname))

                LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(familyMembers) {
                        AvatarChip(memberImage = it.photo)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateToDetail() }
            ) {
                Icon(
                    painter = icArrowRight,
                    contentDescription = "Ver detalles",
                    tint = KidemmaColors.Icon,
                    modifier = Modifier.size(30.dp)
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaFamilyCardItemPreview() {
    KidemmaFamilyCardItem(
        familyName = "Familia Pérez",
        familyNickname = "Los Pérez",
        onNavigateToDetail = {}
    )
}
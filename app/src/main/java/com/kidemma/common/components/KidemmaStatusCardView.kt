package com.kidemma.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.models.CardTextElementUiModel
import com.kidemma.common.models.HeaderElementUiModel
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTypography
import com.kidemma.home_admin.tabs.home.presentation.HomeAdminMock

/*
 * File: KidemmaStatusCardView
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 24/02/26
 * Last modified: 09/03/26
 */

@Composable
fun KidemmaStatusCardView(
    headerItem: HeaderElementUiModel,
    dataList: List<CardTextElementUiModel>,
    onClick: () -> Unit
) {
    KidemmaCard (
        onClick = onClick
    ){
        Column {
            KidemmaCardViewHeader(
                headerItem = headerItem
            )
            CardViewBody(
                dataList = dataList
            )
        }
    }
}

@Composable
private fun KidemmaCardViewHeader(
    headerItem: HeaderElementUiModel
) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {

        headerItem.headerIconID?.let { icon ->
            Image(
                painter = painterResource(id = icon),
                contentDescription = stringResource(headerItem.headerIconDescription),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        KidemmaLabelLarge(
            text = stringResource(headerItem.title)
        )
    }
    HorizontalDivider(
        thickness = 0.5.dp,
        color = KidemmaColors.Divider
    )
}

@Composable
private fun CardViewBody(
    dataList: List<CardTextElementUiModel>
) {
    LazyColumn {
        itemsIndexed(dataList) { index, item ->
            KidemmaCardButton(
                cardItem = item
            )

            if(index < dataList.lastIndex) {
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = KidemmaColors.Divider
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaStatusCardViewPreview() {
    KidemmaStatusCardView(
        headerItem = HeaderElementUiModel(title = R.string.testing_text, headerIconDescription = R.string.testing_text),
        dataList = HomeAdminMock.classStatusList,
        onClick = { }
    )
}
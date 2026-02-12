package com.kidemma.home_admin.tabs.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.common.components.KidemmaStatusCardView
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: HomeAdminScreen
 * Description: [Short Description]
 *
 * Created by: Javier Cuéllar
 * Created on: 24/02/26
 * Last modified: 09/03/26
 */

@Composable
fun HomeAdminTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(all = 16.dp)
    ) {
        KidemmaStatusCardView(
            headerItem = HomeAdminContentProvider.classStatusHeader,
            dataList = HomeAdminMock.classStatusList
        ) { }
        KidemmaStatusCardView(
            headerItem = HomeAdminContentProvider.paymentStatusHeader,
            dataList = HomeAdminMock.paymentStatusList
        ) { }
        KidemmaStatusCardView(
            headerItem = HomeAdminContentProvider.complaintsHeader,
            dataList = HomeAdminMock.complaintsList
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAdminScreenPreview() {
    HomeAdminTabScreen()
}
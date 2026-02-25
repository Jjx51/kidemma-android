package com.kidemma.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kidemma.common.domain.models.KidemmaTopBarUiModel
import com.kidemma.common.presentation.TopBarContentProvider
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.home_admin.domain.model.TopBarUserProfileUiModel

/*
 * File: KidemmaTopAppBar
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidemmaTopAppBar(
    topBarUserProfileUiModel: TopBarUserProfileUiModel,
    uiData: KidemmaTopBarUiModel = TopBarContentProvider.getLoginUiData(),
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = KidemmaColors.Title
        ),

        navigationIcon = {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                ProfileAvatar(
                    name = topBarUserProfileUiModel.profileName,
                    image = topBarUserProfileUiModel.profileImage,
                    hasBorder = false,
                    onClick = onProfileClick
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalSpacerSmall()

                Image(
                    painter = painterResource(id = uiData.appLogo.resId),
                    contentDescription = stringResource(uiData.appLogo.contentDescription),
                    modifier = Modifier.size(uiData.appLogo.size)
                )

                HorizontalSpacerSmall()

                Text(
                    text = stringResource(id = uiData.appName),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },

        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = uiData.notificationIcon.icon,
                    contentDescription = stringResource(uiData.notificationIcon.contentDescription),
                    tint = uiData.notificationIcon.tint ?: LocalContentColor.current
                )
            }
        }
    )
}
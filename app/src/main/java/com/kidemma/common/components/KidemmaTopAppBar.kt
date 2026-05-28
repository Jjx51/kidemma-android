package com.kidemma.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import com.kidemma.common.components.KidemmaHorizontalSpacer
import com.kidemma.common.components.KidemmaSpacerSize
import com.kidemma.common.domain.models.KidemmaTopBarUiModel
import com.kidemma.common.presentation.TopBarContentProvider
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.home_admin.domain.model.TopBarProfileAvatarUiModel

/*
 * File: KidemmaTopAppBar
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 26/02/26
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidemmaTopAppBar(
    topBarProfileAvatarUiModel: TopBarProfileAvatarUiModel,
    uiModel: KidemmaTopBarUiModel = TopBarContentProvider.getLoginUiData(),
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
                KidemmaProfileAvatar(
                    name = topBarProfileAvatarUiModel.profileName,
                    image = topBarProfileAvatarUiModel.profileImage,
                    hasBorder = false,
                    onClick = onProfileClick
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                KidemmaHorizontalSpacer(size = KidemmaSpacerSize.Small)
                Image(
                    painter = painterResource(id = uiModel.appLogo.resId),
                    contentDescription = stringResource(uiModel.appLogo.contentDescription),
                    modifier = Modifier.size(uiModel.appLogo.size)
                )
                KidemmaHorizontalSpacer(size = KidemmaSpacerSize.Small)
                KidemmaLabelMedium(text = stringResource(id = uiModel.appName))
            }
        },

        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = uiModel.notificationIcon.icon,
                    contentDescription = stringResource(uiModel.notificationIcon.contentDescription),
                    tint = uiModel.notificationIcon.tint ?: LocalContentColor.current
                )
            }
        }
    )
}
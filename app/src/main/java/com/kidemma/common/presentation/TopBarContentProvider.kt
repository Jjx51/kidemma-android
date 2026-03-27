package com.kidemma.common.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.domain.models.KidemmaTopBarUiModel
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.common.ui.theme.KidemmaColors

/*
 * File: TopBarContentProvider
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
object TopBarContentProvider {

    val KIDEMMA_LOGO_SIZE = 24.dp

    fun getLoginUiData(): KidemmaTopBarUiModel {
        return KidemmaTopBarUiModel(
            appLogo = ImageUiModel(
                resId = R.drawable.kidemma_logo2,
                contentDescription = R.string.top_app_bar_logo_content_description,
                size = KIDEMMA_LOGO_SIZE
            ),
            appName = R.string.app_name,
            notificationIcon = IconUiModel(
                contentDescription = R.string.top_app_bar_notification_content_description,
                icon = Icons.Default.Notifications,
                tint = KidemmaColors.Icon
            )
        )
    }
}

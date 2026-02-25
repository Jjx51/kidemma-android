package com.kidemma.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.presentation.TopBarContentProvider
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.R
import org.junit.Test

/*
 * File: TopBarContentProviderTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
class TopBarContentProviderTest {

    @Test
    fun `when getBottomNavigationItems, then return correct data`() {
        // WHEN
        val uiData = TopBarContentProvider.getLoginUiData()

        // THEN
        assertThat(uiData.appName).isEqualTo(R.string.app_name)
        assertThat(uiData.appLogo.resId).isEqualTo(R.drawable.kidemma_logo2)
        assertThat(uiData.appLogo.contentDescription).isEqualTo(R.string.top_app_bar_logo_content_description)
        assertThat(uiData.appLogo.size).isEqualTo(24.dp)
        assertThat(uiData.notificationIcon.contentDescription).isEqualTo(R.string.top_app_bar_notification_content_description)
        assertThat(uiData.notificationIcon.icon).isEqualTo(Icons.Default.Notifications)
        assertThat(uiData.notificationIcon.tint).isEqualTo(KidemmaColors.Icon)
    }
}
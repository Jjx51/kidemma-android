package com.kidemma.homeAdmin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import com.google.common.truth.Truth.assertThat
import com.kidemma.common.navigation.AdminRoute
import com.kidemma.homeAdmin.presentation.AdminNavigationContentProvider
import com.kidemma.R
import org.junit.Test

/*
 * File: AdminNavigationContentProviderTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 25/02/26
 * Last modified: 25/02/26
 */
class AdminNavigationContentProviderTest {

    @Test
    fun `getBottomNavigationItems returns correct list of 5 items with proper configuration`() {
        // WHEN
        val items = AdminNavigationContentProvider.getBottomNavigationItems()

        // THEN
        assertThat(items).hasSize(5)

        with(items[0]) {
            assertThat(route).isEqualTo(AdminRoute.Home)
            assertThat(label).isEqualTo(R.string.admin_nav_home)
            assertThat(selectedIcon).isEqualTo(Icons.Filled.Home)
            assertThat(unselectedIcon).isEqualTo(Icons.Outlined.Home)
        }

        with(items[1]) {
            assertThat(route).isEqualTo(AdminRoute.Children)
            assertThat(label).isEqualTo(R.string.admin_nav_children)
            assertThat(selectedIcon).isEqualTo(Icons.Filled.Face)
            assertThat(unselectedIcon).isEqualTo(Icons.Outlined.Face)
        }

        with(items[2]) {
            assertThat(route).isEqualTo(AdminRoute.Agenda)
            assertThat(label).isEqualTo(R.string.admin_nav_agenda)
            assertThat(selectedIcon).isEqualTo(Icons.Filled.DateRange)
            assertThat(unselectedIcon).isEqualTo(Icons.Outlined.DateRange)
        }

        with(items[3]) {
            assertThat(route).isEqualTo(AdminRoute.Families)
            assertThat(label).isEqualTo(R.string.admin_nav_families)
            assertThat(selectedIcon).isEqualTo(Icons.Filled.Groups)
            assertThat(unselectedIcon).isEqualTo(Icons.Outlined.Groups)
        }

        with(items[4]) {
            assertThat(route).isEqualTo(AdminRoute.Other)
            assertThat(label).isEqualTo(R.string.admin_nav_other)
            assertThat(selectedIcon).isEqualTo(Icons.Filled.Menu)
            assertThat(unselectedIcon).isEqualTo(Icons.Outlined.Menu)
        }
    }
}

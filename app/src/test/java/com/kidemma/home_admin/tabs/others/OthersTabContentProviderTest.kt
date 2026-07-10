package com.kidemma.home_admin.tabs.others

import com.google.common.truth.Truth.assertThat
import com.kidemma.home_admin.tabs.others.data.OthersTabOptionUiModel
import com.kidemma.home_admin.tabs.others.data.OtherSTabSectionHeaderUiModel
import com.kidemma.home_admin.tabs.others.presentation.MenuItemId
import com.kidemma.home_admin.tabs.others.presentation.OthersTabContentProvider
import org.junit.Test

/*
 * File: OthersTabContentProviderTest
 * Description: Unit tests for OthersTabContentProvider
 * Created by: Lino Alonso Hdez
 * Created on: 16/03/26
 * Last modified: 16/03/26
 */
class OthersTabContentProviderTest {

    // region isAdminUser = true

    @Test
    fun `given isAdminUser true, when getMenuOptions, then returns all items`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = true)

        // --- THEN ---
        assertThat(items).hasSize(7)
    }

    @Test
    fun `given isAdminUser true, when getMenuOptions, then admin panel option is included`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = true)

        // --- THEN ---
        assertThat(items.any { it.id == MenuItemId.ADMIN_PANEL }).isTrue()
    }

    @Test
    fun `given isAdminUser true, when getMenuOptions, then section header is included`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = true)

        // --- THEN ---
        assertThat(items.any { it is OtherSTabSectionHeaderUiModel }).isTrue()
    }

    // region isAdminUser = false

    @Test
    fun `given isAdminUser false, when getMenuOptions, then returns only non-admin items`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = false)

        // --- THEN ---
        assertThat(items).hasSize(5)
    }

    @Test
    fun `given isAdminUser false, when getMenuOptions, then admin panel option is excluded`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = false)

        // --- THEN ---
        assertThat(items.any { it.id == MenuItemId.ADMIN_PANEL }).isFalse()
    }

    @Test
    fun `given isAdminUser false, when getMenuOptions, then section header is excluded`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = false)

        // --- THEN ---
        assertThat(items.any { it is OtherSTabSectionHeaderUiModel }).isFalse()
    }

    @Test
    fun `given isAdminUser false, when getMenuOptions, then all returned items are options`() {
        // --- WHEN ---
        val items = OthersTabContentProvider.getMenuOptions(isAdminUser = false)

        // --- THEN ---
        assertThat(items.all { it is OthersTabOptionUiModel }).isTrue()
    }

}

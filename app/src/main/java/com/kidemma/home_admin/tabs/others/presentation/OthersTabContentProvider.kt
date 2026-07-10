package com.kidemma.home_admin.tabs.others.presentation

import com.kidemma.R
import com.kidemma.common.navigation.AppRoute
import com.kidemma.home_admin.tabs.others.data.OthersTabOptionUiModel
import com.kidemma.home_admin.tabs.others.data.OtherSTabSectionHeaderUiModel
import com.kidemma.home_admin.tabs.others.data.OthersTabScreenItem

/*
 * File: OthersTabContentProvider
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 12/03/26
 */
object OthersTabContentProvider {

    private val adminOnlyItemIds: Set<String> = setOf(
        MenuItemId.ADMIN_PANEL,
        MenuItemId.HEADER_SECTION
    )

    fun getMenuOptions(isAdminUser: Boolean): List<OthersTabScreenItem> {
        val menuItems = buildMenuItems()
        return menuItems
            .takeIf { isAdminUser.not() }
            ?.filter { it.id !in adminOnlyItemIds }
            ?: menuItems
    }

    private fun buildMenuItems(): List<OthersTabScreenItem> = listOf(
        OthersTabOptionUiModel(
            id = MenuItemId.ADMIN_PANEL,
            iconRes = R.drawable.ic_admin_dashboard,
            titleText = R.string.administration_panel,
            route = AppRoute.AdminPanel
        ),
        OtherSTabSectionHeaderUiModel,
        OthersTabOptionUiModel(
            id = MenuItemId.ABOUT_US,
            iconRes = R.drawable.ic_who_we_are,
            titleText = R.string.others_about_us,
            route = AppRoute.AboutUs
        ),
        OthersTabOptionUiModel(
            id = MenuItemId.STAFF,
            iconRes = R.drawable.ic_staff,
            titleText = R.string.others_staff,
            route = AppRoute.Staff
        ),
        OthersTabOptionUiModel(
            id = MenuItemId.CONTACT,
            iconRes = R.drawable.ic_contact,
            titleText = R.string.others_contact_and_location,
            route = AppRoute.Contact
        ),
        OthersTabOptionUiModel(
            id = MenuItemId.ALLIANCES,
            iconRes = R.drawable.ic_alliances,
            titleText = R.string.others_alliances,
            route = AppRoute.Alliances
        ),
        OthersTabOptionUiModel(
            id = MenuItemId.FEEDBACK,
            iconRes = R.drawable.ice_feedback,
            titleText = R.string.others_complaints_and_suggestions,
            route = AppRoute.Complaints
        )
    )
}

object MenuItemId {
    const val HEADER_SECTION = "id_header_section"
    const val ADMIN_PANEL = "id_admin_panel"
    const val ABOUT_US = "id_about_us"
    const val STAFF = "id_staff"
    const val CONTACT = "id_contact"
    const val ALLIANCES = "id_alliances"
    const val FEEDBACK = "id_feedback"
}
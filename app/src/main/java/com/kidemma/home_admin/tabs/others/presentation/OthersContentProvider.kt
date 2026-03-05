package com.kidemma.home_admin.tabs.others.presentation

import com.kidemma.R
import com.kidemma.home_admin.tabs.others.data.OthersPageUiModel

/*
 * File: OthersContentProvider
 * Description: [Short description]
 *
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 04/03/26
 */
object OthersContentProvider {

    const val ADMIN_PANEL = "admin_panel"
    const val ABOUT_US = "about_us"
    const val STAFF = "staff"
    const val CONTACT = "contact"
    const val ALLIANCES = "alliances"
    const val COMPLAINTS = "complaints"

    fun options() = mapOf(
        ADMIN_PANEL to OthersPageUiModel(
            R.drawable.ic_admin_dashboard,
            R.string.administration_panel
        ),
        ABOUT_US to OthersPageUiModel(
            R.drawable.ic_who_we_are,
            R.string.others_about_us
        ),
        STAFF to OthersPageUiModel(
            R.drawable.ic_staff,
            R.string.others_staff,
        ),
        CONTACT to OthersPageUiModel(
            R.drawable.ic_contact,
            R.string.others_contact_and_location,
        ),
        ALLIANCES to OthersPageUiModel(
            R.drawable.ic_alliances,
            R.string.others_alliances,
        ),
        COMPLAINTS to OthersPageUiModel(
            R.drawable.ice_feedback,
            R.string.others_complaints_and_suggestions,
        )
    )

    fun sectionTitle() = R.string.public_links
}

package com.kidemma.home_admin.tabs.others.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kidemma.common.navigation.AppRoute
import com.kidemma.home_admin.tabs.others.presentation.MenuItemId

/*
 * File: OthersTabOptionUiModel
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 04/03/26
 * Last modified: 12/03/26
 */

sealed interface OthersTabScreenItem {
    val id: String
}

data class OthersTabOptionUiModel(
    override val id: String,
    @param:DrawableRes val iconRes: Int,
    @param:StringRes val titleText: Int,
    val route: AppRoute
) : OthersTabScreenItem

data object OtherSTabSectionHeaderUiModel : OthersTabScreenItem {
    override val id: String = MenuItemId.HEADER_SECTION
}

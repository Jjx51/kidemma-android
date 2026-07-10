package com.kidemma.my_profile.data

import androidx.annotation.StringRes
import com.kidemma.common.navigation.AppRoute
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.my_profile.presentation.MyProfileContract

/**
 * Description: UI model for the MyProfileScreen.
 * This can be used to hold any data that the screen needs to display.
 * @author Arturo Rivera Morales
 * Created on: 21/05/26
 */
data class MyProfileScreenUiModel(
    @param:StringRes val title: Int,
    val options: List<MyProfileScreenOptionUiModel>,
)

data class MyProfileScreenOptionUiModel(
    val icon: IconUiModel,
    @param:StringRes val title: Int,
    val action: ProfileOptionAction,
    val intent: MyProfileContract.Intent,
)

sealed interface ProfileOptionAction {
    data class Navigate(val route: AppRoute) : ProfileOptionAction
    data class Toggle(val isChecked: Boolean) : ProfileOptionAction
}
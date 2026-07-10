package com.kidemma.my_profile.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Policy
import com.kidemma.R
import com.kidemma.common.navigation.AppRoute
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.my_profile.data.MyProfileScreenOptionUiModel
import com.kidemma.my_profile.data.MyProfileScreenUiModel
import com.kidemma.my_profile.data.ProfileOptionAction

/**
 * Description: Factory for creating MyProfileScreenUiModel instances with sample data.
 * This is useful for testing and previewing the UI without needing to set up the entire data
 *
 */
object MyProfileScreenUiFactory {

    fun getMyProfileScreenData(isDarkThemeEnabled: Boolean): MyProfileScreenUiModel {
        return MyProfileScreenUiModel(
            title = R.string.my_profile_screen_title,
            options = getMyProfileScreenOptions(isDarkThemeEnabled)
        )
    }


    private fun getMyProfileScreenOptions(isDarkThemeEnabled: Boolean): List<MyProfileScreenOptionUiModel> {
        return listOf(
            getLanguageOption(),
            getThemeOption(isDarkThemeEnabled),
            getTermsAndConditionsOption(),
            getPrivacyPolicyOption(),
            getLogoutOption(),
        )
    }


    private fun getLanguageOption(): MyProfileScreenOptionUiModel {
        return MyProfileScreenOptionUiModel(
            icon = IconUiModel(
                icon = Icons.Default.Language,
                contentDescription = R.string.my_profile_screen_language_option,
            ),
            title = R.string.my_profile_screen_language_option,
            action = ProfileOptionAction.Navigate(route = AppRoute.MyProfile),
            intent = MyProfileContract.Intent.OnChangeLanguageClicked,
        )
    }

    private fun getThemeOption(isDarkThemeEnabled: Boolean): MyProfileScreenOptionUiModel {
        return MyProfileScreenOptionUiModel(
            icon = IconUiModel(
                icon = Icons.Default.DarkMode,
                contentDescription = R.string.my_profile_screen_dark_theme_option,
            ),
            title = R.string.my_profile_screen_dark_theme_option,
            action = ProfileOptionAction.Toggle(isChecked = isDarkThemeEnabled),
            intent = MyProfileContract.Intent.OnChangeThemeClicked,
        )
    }

    private fun getTermsAndConditionsOption(): MyProfileScreenOptionUiModel {
        return MyProfileScreenOptionUiModel(
            icon = IconUiModel(
                icon = Icons.Default.Policy,
                contentDescription = R.string.my_profile_screen_terms_and_conditions_option,
            ),
            title = R.string.my_profile_screen_terms_and_conditions_option,
            action = ProfileOptionAction.Navigate(route = AppRoute.MyProfile),
            intent = MyProfileContract.Intent.OnShowTermsAndConditionsClicked,
        )
    }

    private fun getPrivacyPolicyOption(): MyProfileScreenOptionUiModel {
        return MyProfileScreenOptionUiModel(
            icon = IconUiModel(
                icon = Icons.Default.Policy,
                contentDescription = R.string.my_profile_screen_privacy_policy_option,
            ),
            title = R.string.my_profile_screen_privacy_policy_option,
            action = ProfileOptionAction.Navigate(route = AppRoute.MyProfile),
            intent = MyProfileContract.Intent.OnShowPrivacyPolicyClicked,
        )
    }

    private fun getLogoutOption(): MyProfileScreenOptionUiModel {
        return MyProfileScreenOptionUiModel(
            icon = IconUiModel(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = R.string.my_profile_screen_logout_option,
            ),
            title = R.string.my_profile_screen_logout_option,
            action = ProfileOptionAction.Navigate(route = AppRoute.Login),
            intent = MyProfileContract.Intent.OnLogoutClicked,
        )
    }

}
package com.kidemma.my_profile.presentation

/**
 * File: OnboardingContract
 *
 * Description: Defines the State, Intent, and Effect for the screen.
 *
 * @author Arturo Rivera Morales
 * Created on: 24/04/26
 * Last modified: 24/04/26
 */
object MyProfileContract {

    data class State(
        val name: String = "",
        val phoneNumber: String = "",
        val profilePictureUrl: String = "",
        val isDarkThemeEnabled: Boolean = false,
    )

    sealed interface Intent {
        data object OnEditProfileClicked : Intent
        data object OnLogoutClicked : Intent
        data object OnChangeLanguageClicked : Intent
        data object OnChangeThemeClicked : Intent
        data object OnShowTermsAndConditionsClicked : Intent
        data object OnShowPrivacyPolicyClicked : Intent
    }

    sealed interface Effect {
        data object NavigateToEditProfile : Effect
        data object NavigateToLogin : Effect
        data object NavigateToLanguageSelection : Effect
        data object NavigateToTermsAndConditions : Effect
        data object NavigateToPrivacyPolicy : Effect



    }

}
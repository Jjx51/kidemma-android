package com.kidemma.profile_menu.profile.presentation


object EditProfileContract {

    data class State(
        val email: String = "",
        val fullName: String = "",
        val mainPhone: String = "",
        val addtionalPhone: String = "",
    )

    sealed interface Intent{
        data class OnFullNameChange(val fullName: String) : Intent
        data class OnMainPhoneChange(val mainPhone: String) : Intent
        data class OnAdditionalPhoneChange(val addtionalPhone: String) : Intent
    }
}
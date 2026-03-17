package com.kidemma.profile_menu.profile.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditProfileViewModel (

) : ViewModel(){
    private val _state = MutableStateFlow(EditProfileContract.State())
    val state: StateFlow<EditProfileContract.State> = _state

    fun onIntent(intent: EditProfileContract.Intent){
        when(intent) {
            is EditProfileContract.Intent.OnFullNameChange -> {
                _state.value = _state.value.copy(
                    fullName = intent.fullName
                )
            }
            is EditProfileContract.Intent.OnMainPhoneChange -> {
                _state.value = _state.value.copy(
                    mainPhone = intent.mainPhone
                )
            }
            is EditProfileContract.Intent.OnAdditionalPhoneChange -> {
                _state.value = _state.value.copy(
                    addtionalPhone = intent.addtionalPhone
                )
            }
        }
    }
}
package com.kidemma.my_profile.presentation

import com.kidemma.my_profile.data.MyProfileScreenUiModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * File: MyProfileViewModel
 * Description: [Short description]
 *
 * @author Arturo Rivera Morales
 * Created on: 21/04/26
 * Last modified: 21/04/26
 */
interface MyProfileViewModel {

    val uiData: MyProfileScreenUiModel
    val state: StateFlow<MyProfileContract.State>
    val effects: SharedFlow<MyProfileContract.Effect>
    fun processIntent(intent: MyProfileContract.Intent)
}
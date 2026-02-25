package com.kidemma.authentication.login.domain.model

import androidx.annotation.StringRes
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.ui.models.OutlinedTextFieldUiModel

/*
 * File: LoginScreenUiModel
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
data class LoginScreenUiModel(
    val logo: ImageUiModel,
    val buttonText: Int,
    val emailTextField: OutlinedTextFieldUiModel,
    val passwordTextField: OutlinedTextFieldUiModel,
    @param:StringRes val errorInvalidEmailFormat: Int,
    @param:StringRes val errorBlankFields: Int,
    @param:StringRes val errorInvalidCredentials: Int,
    @param:StringRes val errorGeneric: Int
)
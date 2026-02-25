package com.kidemma.authentication.login.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.authentication.login.domain.model.LoginScreenUiModel
import com.kidemma.common.ui.models.ImageUiModel
import com.kidemma.common.ui.models.OutlinedTextFieldUiModel
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.common.ui.models.icons.TrailingIconUiModel

/*
 * File: LoginContentProvider
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
object LoginContentProvider {

    val IMAGE_LOGO_SIZE = 130.dp

    fun getLoginScreenData(): LoginScreenUiModel {
        return LoginScreenUiModel(
            logo = getLogoResource(),
            buttonText = getButtonText(),
            emailTextField = getEmailTextField(),
            passwordTextField = getPasswordTextField(),
            errorInvalidEmailFormat = R.string.login_error_invalid_email_format,
            errorBlankFields = R.string.login_error_blank_fields,
            errorInvalidCredentials = R.string.login_error_invalid_credentials,
            errorGeneric = R.string.login_error_generic_error
        )
    }

    private fun getLogoResource(): ImageUiModel {
        return ImageUiModel(
            resId = R.drawable.kidemma_logo,
            contentDescription = R.string.login_logo_content_description,
            size = IMAGE_LOGO_SIZE
        )
    }

    private fun getButtonText(): Int {
        return R.string.login_button_text
    }

    private fun getEmailTextField(): OutlinedTextFieldUiModel {
        return OutlinedTextFieldUiModel(
            label = R.string.login_email_text_field_label,
            leadingIcon = IconUiModel(
                contentDescription = R.string.login_email_text_field_leading_icon_content_description,
                icon = Icons.Default.Email
            )
        )
    }

    private fun getPasswordTextField(): OutlinedTextFieldUiModel {
        return OutlinedTextFieldUiModel(
            label = R.string.login_password_text_field_label,
            leadingIcon = IconUiModel(
                contentDescription = R.string.login_password_text_field_leading_icon_content_description,
                icon = Icons.Default.Lock
            ),
            trailingIcon = TrailingIconUiModel.Stateful(
                activeContentDescription = R.string.login_password_text_field_trailing_icon_hide_password_description,
                activeIcon = Icons.Default.VisibilityOff,
                inactiveContentDescription = R.string.login_password_text_field_trailing_icon_show_password_content_description,
                inactiveIcon = Icons.Default.Visibility,
            ),
        )
    }

}
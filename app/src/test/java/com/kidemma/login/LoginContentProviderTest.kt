package com.kidemma.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth.assertThat
import com.kidemma.authentication.login.presentation.LoginContentProvider
import com.kidemma.R
import com.kidemma.common.ui.models.icons.TrailingIconUiModel
import com.kidemma.extensions.isInstanceOfK
import org.junit.Test

/*
 * File: LoginContentProviderTest
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
class LoginContentProviderTest {

    @Test
    fun getLoginUiData_returnsCorrectData() {
        // WHEN
        val uiData = LoginContentProvider.getLoginScreenData()
        val expectedImageLogoSize = 130.dp

        // THEN
        with(uiData.logo) {
            assertThat(resId).isEqualTo(R.drawable.kidemma_logo)
            assertThat(contentDescription).isEqualTo(R.string.login_logo_content_description)
            assertThat(size).isEqualTo(expectedImageLogoSize)
        }

        assertThat(uiData.buttonText).isEqualTo(R.string.login_button_text)

        with(uiData.emailTextField) {
            assertThat(label).isEqualTo(R.string.login_email_text_field_label)
            assertThat(leadingIcon?.contentDescription)
                .isEqualTo(R.string.login_email_text_field_leading_icon_content_description)
            assertThat(leadingIcon?.icon).isEqualTo(Icons.Default.Email)
            assertThat(leadingIcon).isNotNull()
            assertThat(trailingIcon).isNull()
        }

        with(uiData.passwordTextField) {
            assertThat(label).isEqualTo(R.string.login_password_text_field_label)

            assertThat(leadingIcon?.icon).isEqualTo(Icons.Default.Lock)
            assertThat(leadingIcon?.contentDescription)
                .isEqualTo(R.string.login_password_text_field_leading_icon_content_description)

            assertThat(trailingIcon).isInstanceOfK<TrailingIconUiModel.Stateful>()

            val statefulIcon = trailingIcon as TrailingIconUiModel.Stateful

            assertThat(statefulIcon.activeIcon).isEqualTo(Icons.Default.VisibilityOff)
            assertThat(statefulIcon.activeContentDescription).isEqualTo(R.string.login_password_text_field_trailing_icon_hide_password_description)

            assertThat(statefulIcon.inactiveIcon).isEqualTo(Icons.Default.Visibility)
            assertThat(statefulIcon.inactiveContentDescription).isEqualTo(R.string.login_password_text_field_trailing_icon_show_password_content_description)
        }

        assertThat(uiData.errorInvalidEmailFormat).isEqualTo(R.string.login_error_invalid_email_format)
        assertThat(uiData.errorBlankFields).isEqualTo(R.string.login_error_blank_fields)
        assertThat(uiData.errorInvalidCredentials).isEqualTo(R.string.login_error_invalid_credentials)
    }

}
package com.kidemma.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kidemma.common.ui.models.OutlinedTextFieldUiModel
import com.kidemma.common.ui.models.icons.TrailingIconUiModel
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedErrorBorderColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedErrorIconColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedErrorLabelColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedFocusedBorderColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedFocusedIconColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedFocusedLabelColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedUnfocusedBorderColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedUnfocusedIconColor
import com.kidemma.common.ui.theme.KidemmaColors.OutlinedUnfocusedLabelColor

/*
 * File: KidemmaOutlinedTextField
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */
@Composable
fun KidemmaOutlinedTextField(
    modifier: Modifier = Modifier,
    data: OutlinedTextFieldUiModel,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTrailingIconClick: (() -> Unit)? = null,
    isTrailingIconActive: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = data.label)) },
        singleLine = singleLine,
        isError = isError,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(12.dp),
        supportingText = {
            if (isError && errorMessage != null) {
                Text(text = errorMessage, color = KidemmaColors.Error)
            } else if (data.supportingText != null) {
                Text(text = stringResource(data.supportingText))
            }
        },

        leadingIcon = {
            data.leadingIcon?.let {
                Icon(
                    imageVector = it.icon,
                    contentDescription = stringResource(id = it.contentDescription)
                )
            }
        },

        trailingIcon = {
            GetTrailingIcon(
                trailingIcon = data.trailingIcon,
                onTrailingIconClick = onTrailingIconClick,
                isTrailingIconActive = isTrailingIconActive
            )
        },

        colors = getColorsKidemmaOutlinedTextField()
    )
}

@Composable
fun KidemmaOutlinedTextField(
    modifier: Modifier = Modifier,
    label: Int,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = label)) },
        singleLine = singleLine,
        isError = isError,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(12.dp),
        supportingText = {
//            if (isError && errorMessage != null) {
//                Text(text = errorMessage, color = KidemmaColors.Error)
//            } else if (label.supportingText != null) {
//                Text(text = stringResource(data.supportingText))
//            }
        },
        colors = getColorsKidemmaOutlinedTextField()
    )
}

@Composable
private fun GetTrailingIcon(
    trailingIcon: TrailingIconUiModel?,
    onTrailingIconClick: (() -> Unit)? = null,
    isTrailingIconActive: Boolean = false
) {
    when (trailingIcon) {
        is TrailingIconUiModel.Simple -> {
            if (onTrailingIconClick != null) {
                IconButton(onClick = onTrailingIconClick) {
                    Icon(
                        imageVector = trailingIcon.icon,
                        contentDescription = stringResource(id = trailingIcon.contentDescription)
                    )
                }
            }
        }

        is TrailingIconUiModel.Stateful -> {
            val icon =
                if (isTrailingIconActive) trailingIcon.activeIcon else trailingIcon.inactiveIcon
            val description =
                if (isTrailingIconActive) trailingIcon.activeContentDescription else trailingIcon.inactiveContentDescription

            if (onTrailingIconClick != null) {
                IconButton(onClick = onTrailingIconClick) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(id = description)
                    )
                }
            }
        }

        null -> Unit
    }
}

@Composable
private fun getColorsKidemmaOutlinedTextField(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        focusedBorderColor = OutlinedFocusedBorderColor,
        focusedLabelColor = OutlinedFocusedLabelColor,
        focusedLeadingIconColor = OutlinedFocusedIconColor,
        focusedTrailingIconColor = OutlinedFocusedIconColor,

        unfocusedBorderColor = OutlinedUnfocusedBorderColor,
        unfocusedLabelColor = OutlinedUnfocusedLabelColor,
        unfocusedLeadingIconColor = OutlinedUnfocusedIconColor,
        unfocusedTrailingIconColor = OutlinedUnfocusedIconColor,

        errorBorderColor = OutlinedErrorBorderColor,
        errorLabelColor = OutlinedErrorLabelColor,
        errorLeadingIconColor = OutlinedErrorIconColor,
        errorTrailingIconColor = OutlinedErrorIconColor,
    )
}
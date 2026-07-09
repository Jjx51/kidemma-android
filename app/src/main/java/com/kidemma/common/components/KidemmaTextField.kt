package com.kidemma.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.ui.models.OutlinedTextFieldUiModel
import com.kidemma.common.ui.models.icons.IconUiModel
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
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme

/*
 * File: KidemmaTextField.kt
 * Description: Grouped TextField components for the Kidemma application.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 14/05/26
 * Last modified: 19/05/26
 */

// --- KidemmaTextFieldWithFilter ---

private val TextFieldWithFilterHeight = 60.dp
private val TextFieldWithFilterErrorPadding = 16.dp

@Composable
fun KidemmaTextFieldWithFilter(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit, isError: Boolean = false, errorMessage: String? = null,
    onClickFilter: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            KidemmaCard(
                modifier = Modifier
                    .weight(1f)
                    .height(TextFieldWithFilterHeight)
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(), value = value, onValueChange = onValueChange, isError = isError, placeholder = {
                        KidemmaLabelLarge(
                            text = stringResource(R.string.family_screen_write_something), color = KidemmaColors.PlaceholderForm
                        )
                    }, trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            tint = if (isError) KidemmaColors.Error else KidemmaColors.PlaceholderForm,
                            contentDescription = stringResource(R.string.texfield_with_filter_search_icon),
                            modifier = Modifier.size(KidemmaDimens.IconSizeMedium)
                        )
                    }, singleLine = true, textStyle = MaterialTheme.typography.bodyMedium, colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorContainerColor = Color.White,
                        errorIndicatorColor = Color.Transparent
                    )
                    )
                }
            }

            KidemmaHorizontalSpacer(size = KidemmaSpacerSize.Small)

            KidemmaCard(
                modifier = Modifier
                    .size(TextFieldWithFilterHeight)
                    .clickable { onClickFilter() }) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = stringResource(R.string.texfield_with_filter_filter_icon),
                        tint = KidemmaColors.PlaceholderForm,
                        modifier = Modifier.size(KidemmaDimens.IconSizeMedium)
                    )
                }
            }
        }
        if (isError && errorMessage != null) {
            KidemmaVerticalSpacer(size = KidemmaSpacerSize.ExtraSmall)
            KidemmaLabelSmall(
                text = errorMessage, color = KidemmaColors.Error, modifier = Modifier.padding(start = TextFieldWithFilterErrorPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KidemmaTextFieldWithFilterPreview() {
    KidemmaTheme {
        var text by remember { mutableStateOf("") }
        KidemmaTextFieldWithFilter(
            value = text,
            onValueChange = { text = it },
            onClickFilter = {}
        )
    }
}

// --- KidemmaOutlinedTextField ---

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

@Preview(showBackground = true)
@Composable
fun KidemmaOutlinedTextFieldPreview() {
    KidemmaTheme {
        var text by remember { mutableStateOf("") }
        KidemmaOutlinedTextField(
            data = OutlinedTextFieldUiModel(
                label = R.string.login_email_text_field_label,
                leadingIcon = IconUiModel(
                    contentDescription = R.string.login_email_text_field_leading_icon_content_description,
                    icon = Icons.Default.Email
                )
            ),
            value = text,
            onValueChange = { text = it }
        )
    }
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

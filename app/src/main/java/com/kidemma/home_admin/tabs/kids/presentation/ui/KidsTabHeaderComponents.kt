package com.kidemma.home_admin.tabs.kids.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.KidemmaTextFieldWithFilter
import com.kidemma.common.validation.KidemmaValidationError
import com.kidemma.common.validation.asMessage
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens

/*
 * File: KidsTabHeaderComponents.kt
 * Description: Kids tab header composables including search and view toggles.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 19/05/26
 */

@Composable
fun KidsTabHeader(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isSearchError: Boolean = false,
    searchError: KidemmaValidationError? = null,
    onClickFilter: () -> Unit,
    isGridView: Boolean,
    onToggleGridView: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = KidsTabHeaderUiConstants.HeaderTopPadding,
                start = KidsTabHeaderUiConstants.HorizontalPadding,
                end = KidsTabHeaderUiConstants.HorizontalPadding
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        KidemmaTextFieldWithFilter(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            isError = isSearchError,
            errorMessage = searchError.asMessage(),
            onClickFilter = onClickFilter
        )

        ToggleGridViewButton(
            modifier = Modifier.align(Alignment.End),
            isGridView = isGridView,
            onToggle = onToggleGridView,
        )
    }
}

@Composable
private fun ToggleGridViewButton(
    isGridView: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = if (isGridView) Icons.AutoMirrored.Filled.ViewList else Icons.Filled.GridView
    val iconContentDescription = if (isGridView) stringResource(R.string.kids_list_view_content_description)
    else stringResource(R.string.kids_grid_view_content_description)

    Card(
        onClick = onToggle,
        modifier = modifier
            .size(KidsTabHeaderUiConstants.ButtonSize)
            .clip(CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = KidemmaColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = KidsTabHeaderUiConstants.ButtonElevation),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                tint = KidemmaColors.Icon,
                modifier = Modifier.size(KidemmaDimens.IconSizeSmall),
            )
        }
    }
}

private object KidsTabHeaderUiConstants {
    val HorizontalPadding = 16.dp
    val HeaderTopPadding = 16.dp
    val ButtonSize = 36.dp
    val ButtonElevation = 6.dp
}

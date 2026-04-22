package com.kidemma.home_admin.tabs.kids.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kidemma.R
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.kids.data.KidsTabMockProvider
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnApplyFilter
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnCloseFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnOpenFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.ui.EmptyKidsContent
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsGridView
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsListView
import org.koin.androidx.compose.koinViewModel

/*
 * File: KidsTabScreen.kt
 * Description: Entry composable for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

@Composable
fun KidsTabScreen(modifier: Modifier = Modifier, viewModel: KidsTabViewModelImpl = koinViewModel()) {
    val uiState by viewModel.state.collectAsState()

    KidsTabContent(
        modifier = modifier,
        uiState = uiState,
        onIntent = { intent -> viewModel.processIntent(intent) },
    )
}

@Composable
private fun KidsTabContent(
    modifier: Modifier = Modifier,
    uiState: KidsTabContract.State,
    onIntent: (KidsTabContract.Intent) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(KidemmaColors.Background),
        ) {
            // TODO: Add Laura's filter field
            // KidsTabHeaderComponents()

            KidsTabActions(
                isGridView = uiState.isGridView,
                onOpenFilter = { onIntent(OnOpenFilterDialog) },
                onToggleGridView = { onIntent(KidsTabContract.Intent.OnToggleGridView) }
            )

            KidsTabMainContent(
                isGridView = uiState.isGridView,
                contentState = uiState.content,
                onKidClick = { kidId ->
                    onIntent(KidsTabContract.Intent.OnKidClick(kidId))
                }
            )
        }

        if (uiState.content is KidsTabContract.KidsContentState.Loading) {
            KidemmaLoadingOverlay()
        }
    }

    if (uiState.showFilterDialog) {
        KidsTabFilterDialog(
            onDismiss = { onIntent(OnCloseFilterDialog) },
            onApplyFilter = { filter ->
                onIntent(OnApplyFilter(filter))
            },
        )
    }
}

@Composable
private fun KidsTabActions(
    isGridView: Boolean,
    onOpenFilter: () -> Unit,
    onToggleGridView: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FilterButton(onClick = onOpenFilter)

        ToggleGridViewButton(
            isGridView = isGridView,
            onToggle = onToggleGridView,
        )
    }
}

@Composable
private fun KidsTabMainContent(
    isGridView: Boolean,
    contentState: KidsTabContract.KidsContentState,
    onKidClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (contentState) {
            KidsTabContract.KidsContentState.Loading -> {
                // Handled by the overlay in parent
            }

            KidsTabContract.KidsContentState.Empty -> EmptyKidsContent()

            is KidsTabContract.KidsContentState.Data -> {
                if (isGridView) {
                    KidsGridView(
                        kidDetailList = contentState.kidDetailList,
                        onKidClick = { kidDetail ->
                            onKidClick(kidDetail.kidUiModel.id)
                        },
                    )
                } else {
                    KidsListView(
                        kidDetailList = contentState.kidDetailList,
                        onKidClick = { kidDetail ->
                            onKidClick(kidDetail.kidUiModel.id)
                        },
                    )
                }
            }
        }
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
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = KidemmaColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
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

@Composable
private fun FilterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = KidemmaColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Filled.FilterList,
                contentDescription = stringResource(R.string.kids_open_filter_dialog_content_description),
                tint = KidemmaColors.Icon,
                modifier = Modifier.size(KidemmaDimens.IconSizeSmall),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KidsTabScreenPreview() {
    KidemmaTheme {
        KidsTabContent(
            uiState = KidsTabContract.State(
                content = KidsTabContract.KidsContentState.Data(
                    kidDetailList = KidsTabMockProvider.getKidsDetailList(),
                ),
                isGridView = false,
                showFilterDialog = false,
            ),
            onIntent = {},
        )
    }
}
package com.kidemma.home_admin.tabs.kids.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kidemma.common.components.KidemmaLoadingOverlay
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.home_admin.tabs.kids.data.KidsTabMockProvider
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnApplyFilter
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnCloseFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnOpenFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.ui.EmptyKidsContent
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsGridView
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsListView
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsTabHeader
import org.koin.androidx.compose.koinViewModel

/*
 * File: KidsTabScreen.kt
 * Description: Entry composable for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 19/05/26
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
            KidsTabHeader(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = { query ->
                    onIntent(KidsTabContract.Intent.OnSearchQueryChange(query))
                },
                isSearchError = uiState.isSearchError,
                searchError = uiState.searchError,
                onClickFilter = { onIntent(OnOpenFilterDialog) },
                isGridView = uiState.isGridView,
                onToggleGridView = { onIntent(KidsTabContract.Intent.OnToggleGridView) }
            )

            KidsTabMainContent(
                modifier = Modifier.weight(1f),
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

@Preview(showBackground = true)
@Composable
private fun
        KidsTabScreenPreview() {
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

package com.kidemma.home_admin.tabs.kids.presentation

import androidx.compose.foundation.background
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
import com.kidemma.common.utils.Gender
import com.kidemma.home_admin.tabs.kids.data.KidsTabMockProvider
import com.kidemma.home_admin.tabs.kids.domain.models.KidsTabFilterResult
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnApplyFilter
import com.kidemma.home_admin.tabs.kids.presentation.KidsTabContract.Intent.OnCloseFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.components.KidsTabFilterDialog
import com.kidemma.home_admin.tabs.kids.presentation.ui.EmptyKidsContent
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsGridView
import com.kidemma.home_admin.tabs.kids.presentation.ui.KidsListView
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

/*
 * File: KidsTabScreen.kt
 * Description: Entry composable for the Kids tab screen.
 *
 * Created by: José Manuel Carrillo Torres
 * Created on: 09/03/26
 * Last modified: 09/03/26
 */

@Composable
internal fun KidsTabScreen(modifier: Modifier = Modifier, viewModel: KidsTabViewModelImpl = koinViewModel()) {
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
            // KidsHeader()

            ToggleGridViewButton(isGridView = uiState.isGridView) { onIntent(KidsTabContract.Intent.OnToggleGridView) }

            when (val content = uiState.content) {
                KidsTabContract.KidsContentState.Loading -> {
                    // Handled by the overlay below to ensure it's on top of everything
                }

                KidsTabContract.KidsContentState.Empty -> EmptyKidsContent()

                is KidsTabContract.KidsContentState.Data -> {
                    if (uiState.isGridView) {
                        KidsGridView(
                            kidDetailList = content.kidDetailList,
                            onKidClick = { kidDetail ->
                                onIntent(KidsTabContract.Intent.OnKidClick(kidDetail.kidUiModel.id))
                            },
                        )
                    } else {
                        KidsListView(
                            kidDetailList = content.kidDetailList,
                            onKidClick = { kidDetail ->
                                onIntent(KidsTabContract.Intent.OnKidClick(kidDetail.kidUiModel.id))
                            },
                        )
                    }
                }
            }
        }

        if (uiState.content is KidsTabContract.KidsContentState.Loading) {
            KidemmaLoadingOverlay()
        }
    }

    if (uiState.showFilterDialog) {
        // todo: Pass actual filter values from the state when implementing the dialog
        KidsTabFilterDialog(onDismiss = { onIntent(OnCloseFilterDialog) }) {
            onIntent(
                OnApplyFilter(
                    KidsTabFilterResult(
                        birthday = LocalDate.now().minusYears(2).minusMonths(3), gender = Gender.MALE
                    )
                )
            )
        }
    }
}

@Composable
internal fun ToggleGridViewButton(
    modifier: Modifier = Modifier,
    isGridView: Boolean,
    onToggle: () -> Unit,
) {
    val icon = if (isGridView) Icons.AutoMirrored.Filled.ViewList else Icons.Filled.GridView
    val iconContentDescription = if (isGridView) stringResource(R.string.kids_list_view_content_description)
    else stringResource(R.string.kids_grid_view_content_description)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 16.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
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
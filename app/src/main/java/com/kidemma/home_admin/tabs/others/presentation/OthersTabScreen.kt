package com.kidemma.home_admin.tabs.others.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kidemma.R
import com.kidemma.common.components.KidemmaLabelLarge
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.home_admin.tabs.others.data.OthersTabOptionUiModel
import com.kidemma.home_admin.tabs.others.data.OtherSTabSectionHeaderUiModel
import com.kidemma.home_admin.tabs.others.presentation.components.KidemmaOptionCard
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

/*
 * File: OthersTabScreen
 * Description: [Short description]
 * Created by: Lino Alonso Hdez
 * Created on: 16/02/26
 * Last modified: 12/03/26
 */
@Composable
fun OthersTabScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: OthersTabViewModelImpl = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HandleIOthersScreenEffects(navController = navController, viewModel.effects)
    OthersContent(
        modifier = modifier,
        state = state,
        onIntent = viewModel::processIntent
    )
}


@Composable
private fun OthersContent(
    modifier: Modifier,
    state: OthersTabContract.State,
    onIntent: (OthersTabContract.Intent.OnOptionSelected) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = KidemmaColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.options, key = { it.id }) { item ->
            when (item) {
                is OthersTabOptionUiModel -> {
                    KidemmaOptionCard(
                        icon = item.iconRes,
                        title = item.titleText,
                        onClick = { onIntent(OthersTabContract.Intent.OnOptionSelected(item.route)) }
                    )
                }

                is OtherSTabSectionHeaderUiModel -> KidemmaLabelLarge(text = stringResource(R.string.public_links))
            }

        }
    }
}



@Composable
private fun HandleIOthersScreenEffects(
    navController: NavController,
    effects: SharedFlow<OthersTabContract.Effect>,
) {
    LaunchedEffect(Unit) {
        effects.collectLatest { effect ->
            when (effect) {
                is OthersTabContract.Effect.NavigateTo ->
                    navController.navigate(effect.screen)
            }
        }
    }
}

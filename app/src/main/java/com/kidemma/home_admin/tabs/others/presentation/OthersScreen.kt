package com.kidemma.home_admin.tabs.others.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kidemma.common.navigation.AppRoute
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens.BodyLarge
import com.kidemma.common.ui.theme.KidemmaTypography
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.ABOUT_US
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.ADMIN_PANEL
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.ALLIANCES
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.COMPLAINTS
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.CONTACT
import com.kidemma.home_admin.tabs.others.presentation.OthersContentProvider.STAFF
import com.kidemma.home_admin.tabs.others.presentation.components.KidemmaOptionCard
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

/*
 * File: OthersScreen
 * Description: [Short description]
 *
 * Created by: Lino Alonso Hdez
 * Created on: 16/02/26
 * Last modified: 04/03/26
 */
@Composable
fun OthersScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: OthersViewModelImpl = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HandleSplashEffects(navController = navController, viewModel.effects)
    OthersContent(
        modifier = modifier,
        state = state,
        onIntent = viewModel::processIntent
    )
}


@Composable
private fun OthersContent(
    modifier: Modifier,
    state: OthersContract.State,
    onIntent: (OthersContract.Intent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = KidemmaColors.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        KidemmaOptionCard(
            icon = state.options[ADMIN_PANEL]!!.iconUiModel,
            title = state.options[ADMIN_PANEL]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.AdminPanelClicked) }
        )
        OthersSectionTitle(text = state.sectionTitle)
        KidemmaOptionCard(
            icon = state.options[ABOUT_US]!!.iconUiModel,
            title = state.options[ABOUT_US]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.AboutUsClicked) }
        )

        KidemmaOptionCard(
            icon = state.options[STAFF]!!.iconUiModel,
            title = state.options[STAFF]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.StaffClicked) }
        )

        KidemmaOptionCard(
            icon = state.options[CONTACT]!!.iconUiModel,
            title = state.options[CONTACT]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.ContactClicked) }
        )

        KidemmaOptionCard(
            icon = state.options[ALLIANCES]!!.iconUiModel,
            title = state.options[ALLIANCES]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.AlliancesClicked) }
        )

        KidemmaOptionCard(
            icon = state.options[COMPLAINTS]!!.iconUiModel,
            title = state.options[COMPLAINTS]!!.titleText,
            onClick = { onIntent(OthersContract.Intent.ComplaintsClicked) }
        )
    }
}


@Composable
private fun OthersSectionTitle(@StringRes text: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(text),
        style = KidemmaTypography.labelLarge.copy(fontSize = BodyLarge),
        modifier = modifier
    )
}

@Composable
fun HandleSplashEffects(
    navController: NavHostController,
    effects: SharedFlow<OthersContract.Effect>,
) {
    LaunchedEffect(Unit) {
        effects.collectLatest { effect ->
            when (effect) {
                is OthersContract.Effect.NavigateTo ->
                    navController.navigate(effect.screen) {
                        popUpTo<AppRoute.Onboarding> { inclusive = true }
                        launchSingleTop = true
                    }
            }
        }
    }
}

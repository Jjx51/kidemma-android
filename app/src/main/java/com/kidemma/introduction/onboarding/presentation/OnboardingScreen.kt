package com.kidemma.introduction.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kidemma.R
import com.kidemma.common.components.KidemmaBodyMedium
import com.kidemma.common.components.KidemmaHeadlineSmall
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.components.KidemmaTertiaryButton
import com.kidemma.common.navigation.AppRoute
import com.kidemma.common.ui.theme.KidemmaColors
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

/*
 * File: OnboardingScreen
 * Description: [Short description]
 *
 * Created by: Jose Carrillo
 * Created on: 13/11/25
 * Last modified: 24/02/26
 */

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModelImpl = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HandleSplashEffects(navController = navController, viewModel = viewModel)
    OnboardingContent(
        state = state,
        onNextClicked = { viewModel.processIntent(OnboardingContract.Intent.OnNextClicked) },
        onSkipClicked = { viewModel.processIntent(OnboardingContract.Intent.OnSkipClicked) }
    )
}

@Composable
fun HandleSplashEffects(
    navController: NavHostController,
    viewModel: OnboardingViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effects.collectLatest { effect ->
            when (effect) {
                is OnboardingContract.Effect.NavigateTo ->
                    navController.navigate(effect.screen) {
                        popUpTo<AppRoute.Onboarding> { inclusive = true }
                        launchSingleTop = true
                    }
            }
        }
    }
}

@Composable
fun OnboardingContent(
    state: OnboardingContract.State,
    onNextClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        KidemmaTertiaryButton(
            text = stringResource(R.string.onboarding_skip_button),
            modifier = Modifier.align(Alignment.End),
            onClick = onSkipClicked
        )
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(state.currentPage.imageUiModel.size),
            painter = painterResource(state.currentPage.imageUiModel.resId),
            contentDescription = stringResource(state.currentPage.imageUiModel.contentDescription)
        )
        Spacer(Modifier.height(70.dp))
        KidemmaHeadlineSmall(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(state.currentPage.titleText),
            textAlign = TextAlign.Center
        )
        KidemmaBodyMedium(
            text = stringResource(state.currentPage.bodyText),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        KidemmaPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(state.currentPage.buttonText),
            onClick = onNextClicked
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

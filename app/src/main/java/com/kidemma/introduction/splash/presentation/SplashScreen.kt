package com.kidemma.introduction.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.kidemma.common.navigation.AppRoute
import org.koin.androidx.compose.koinViewModel

/*
 * File: SplashScreen
 * Description: [Short description]
 *
 * Created by: Jorge Luis Hernández Núñez
 * Created on: 24/02/26
 * Last modified: 24/02/26
 */

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModelImpl = koinViewModel(),
){
    HandleSplashEffects(viewModel, navController)
    ValidateDestiny(viewModel)
}

@Composable
private fun HandleSplashEffects(
    viewModel: SplashViewModelImpl,
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SplashContract.Effect.NavigateTo -> {
                    navController.navigate(route = effect.destination) {
                        popUpTo<AppRoute.Splash> { inclusive = true }
                    }
                }
            }
        }
    }
}

@Composable
private fun ValidateDestiny(viewModel: SplashViewModelImpl) {
    LaunchedEffect(Unit) {
        viewModel.processIntent(SplashContract.Intent.ValidateDestiny)
    }
}

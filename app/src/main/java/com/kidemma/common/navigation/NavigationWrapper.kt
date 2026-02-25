package com.kidemma.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kidemma.introduction.presentation.onboarding.OnboardingScreen
import com.kidemma.introduction.splash.presentation.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = AppRoute.Splash
    ) {
        composable<AppRoute.Splash> { SplashScreen(navController) }
        composable<AppRoute.Onboarding> { OnboardingScreen(navController) }
    }
}
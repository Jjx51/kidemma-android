package com.kidemma.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kidemma.introduction.presentation.Onboarding.OnboardingScreen
import com.kidemma.introduction.presentation.Splash.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen { exampleArg ->
                navController.navigate(OnboardingArguments(exampleArg))
            }
        }
        composable<OnboardingArguments> { backstackEntry ->
            val onboardingArguments: OnboardingArguments = backstackEntry.toRoute()
            OnboardingScreen(onboardingArguments.exampleArg)
        }
    }
}
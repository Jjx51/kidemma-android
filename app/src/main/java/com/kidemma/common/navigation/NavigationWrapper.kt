package com.kidemma.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kidemma.authentication.login.presentation.LoginScreen
import com.kidemma.home_admin.presentation.AdminMainScreen
import com.kidemma.home_parent.presentation.ParentMainScreen
import com.kidemma.introduction.onboarding.presentation.OnboardingScreen
import com.kidemma.introduction.splash.presentation.SplashScreen
import com.kidemma.my_profile.presentation.MyProfileScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = AppRoute.Splash
    ) {
        composable<AppRoute.Splash> { SplashScreen(navController) }
        composable<AppRoute.Onboarding> { OnboardingScreen(navController) }
        composable<AppRoute.Login> { LoginScreen(navController) }
        composable<AppRoute.AdminMain> { AdminMainScreen(navController) }
        composable<AppRoute.UserMain> { ParentMainScreen() }
        composable<AppRoute.MyProfile> { MyProfileScreen(navController) }
    }
}
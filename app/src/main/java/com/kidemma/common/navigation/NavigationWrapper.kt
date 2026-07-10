package com.kidemma.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kidemma.authentication.login.presentation.LoginScreen
import com.kidemma.common.components.PlaceholderScreen
import com.kidemma.home_admin.presentation.AdminMainScreen
import com.kidemma.home_parent.presentation.ParentMainScreen
import com.kidemma.introduction.onboarding.presentation.OnboardingScreen
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
        composable<AppRoute.Login> { LoginScreen(navController) }
        composable<AppRoute.AdminMain> { AdminMainScreen(navController) }
        composable<AppRoute.UserMain> { ParentMainScreen() }
        composable<AppRoute.AdminPanel> { PlaceholderScreen("Admin Panel") }
        composable<AppRoute.AboutUs> { PlaceholderScreen("About Us") }
        composable<AppRoute.Staff> { PlaceholderScreen("Staff") }
        composable<AppRoute.Contact> { PlaceholderScreen("Contact") }
        composable<AppRoute.Alliances> { PlaceholderScreen("Alliance") }
        composable<AppRoute.Complaints> { PlaceholderScreen("Complaints") }
    }
}
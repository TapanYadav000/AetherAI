package com.tapan.aetherai.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.tapan.aetherai.presentation.onboarding.OnboardingScreen

@Composable
fun AetherNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AetherDestination.Onboarding.route
    ) {

        composable(
            route = AetherDestination.Onboarding.route
        ) {
            OnboardingScreen()
        }
    }
}
package com.tapan.aetherai.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapan.aetherai.presentation.home.HomeScreen
import com.tapan.aetherai.presentation.onboarding.OnboardingScreen
import com.tapan.aetherai.presentation.profile.EditProfileScreen
import com.tapan.aetherai.presentation.startup.StartupScreen

@Composable
fun AetherNavGraph(
    darkTheme: Boolean,
    onThemeToggle: () -> Unit
){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AetherDestination.Startup.route
    ) {
        composable(
            route = AetherDestination.Startup.route
        ) {

            StartupScreen(

                onNavigateToOnboarding = {

                    navController.navigate(
                        AetherDestination.Onboarding.route
                    ) {
                        popUpTo(
                            AetherDestination.Startup.route
                        ) {
                            inclusive = true
                        }
                    }
                },

                onNavigateToHome = {

                    navController.navigate(
                        AetherDestination.Home.route
                    ) {
                        popUpTo(
                            AetherDestination.Startup.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = AetherDestination.Onboarding.route
        ) {
            OnboardingScreen(
                onNavigateToHome = {
                    navController.navigate(
                        AetherDestination.Home.route
                    ) {
                        popUpTo(
                            AetherDestination.Onboarding.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = AetherDestination.EditProfile.route
        ) {
            EditProfileScreen()
        }
        composable(
            route = AetherDestination.Home.route
        ) {

            HomeScreen(
                darkTheme = darkTheme,
                onThemeToggle = onThemeToggle,
                onEditProfileClick = {
                    navController.navigate(
                        AetherDestination.EditProfile.route
                    )
                }
            )
        }
    }
}
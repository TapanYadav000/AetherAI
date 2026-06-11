package com.tapan.aetherai.presentation.navigation

sealed class AetherDestination(val route: String) {

    data object Onboarding : AetherDestination("onboarding")

    data object Home : AetherDestination("home")
}
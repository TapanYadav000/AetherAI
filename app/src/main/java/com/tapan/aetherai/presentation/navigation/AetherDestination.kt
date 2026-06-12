package com.tapan.aetherai.presentation.navigation

sealed class AetherDestination(val route: String) {

    data object Startup : AetherDestination("startup")

    data object Onboarding : AetherDestination("onboarding")

    data object Home : AetherDestination("home")

    data object EditProfile : AetherDestination("edit_profile")
}
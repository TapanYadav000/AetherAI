package com.tapan.aetherai.presentation.startup

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StartupScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: StartupViewModel = hiltViewModel()
) {

    val hasProfile by viewModel.hasProfile.collectAsState()

    LaunchedEffect(hasProfile) {

        when (hasProfile) {

            true -> onNavigateToHome()

            false -> onNavigateToOnboarding()

            null -> Unit
        }
    }
}
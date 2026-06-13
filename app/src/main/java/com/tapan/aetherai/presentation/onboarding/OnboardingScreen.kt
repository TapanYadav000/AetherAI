package com.tapan.aetherai.presentation.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapan.aetherai.presentation.onboarding.page.*

@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit
) {

    var isUserInfoValid by remember {
        mutableStateOf(false)
    }
    var isTraitSelectionValid by remember {
        mutableStateOf(false)
    }
    val viewModel: OnboardingViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            LinearProgressIndicator(
                progress = {
                    (state.currentPage + 1) / 3f
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp)
            ) {

                when (state.currentPage) {

                    0 -> WelcomePage()

                    1 -> UserInfoPage(
                        state = state,
                        viewModel = viewModel,
                        onValidationChanged = {
                            isUserInfoValid = it
                        }
                    )

                    2 -> TraitSelectionPage(
                        state = state,
                        viewModel = viewModel,
                        onValidationChanged = {
                            isTraitSelectionValid = it
                        }
                    )
                }

            }
            Button(
                onClick = {

                    if (state.currentPage < 2) {
                        viewModel.nextPage()
                    } else {
                        viewModel.finishOnboarding()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 24.dp
                    ),
                enabled = when (state.currentPage) {

                    0 -> true

                    1 -> isUserInfoValid

                    2 -> isTraitSelectionValid

                    else -> false
                }
            ) {

                Text(
                    text =
                        if (state.currentPage == 2)
                            "Finish"
                        else
                            "Continue"
                )
            }
        }

        LaunchedEffect(state.isCompleted) {

            if (state.isCompleted) {
                onNavigateToHome()
            }
        }
    }
}
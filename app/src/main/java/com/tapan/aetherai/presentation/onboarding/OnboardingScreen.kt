package com.tapan.aetherai.presentation.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tapan.aetherai.presentation.onboarding.page.*

@Composable
fun OnboardingScreen() {

    var isUserInfoValid by remember {
        mutableStateOf(false)
    }
    var isTraitSelectionValid by remember {
        mutableStateOf(false)
    }
    val viewModel: OnboardingViewModel = viewModel()

    val state by viewModel.state.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.weight(1f)
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

            Button(
                onClick = {
                    viewModel.nextPage()
                },
                enabled = when (state.currentPage) {

                    0 -> true

                    1 -> isUserInfoValid

                    2 -> isTraitSelectionValid

                    else -> false
                }
            ) {
                Text(
                    text = if (state.currentPage == 2)
                        "Finish"
                    else
                        "Next"
                )
            }
        }
    }
}
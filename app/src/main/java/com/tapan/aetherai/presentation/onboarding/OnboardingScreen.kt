package com.tapan.aetherai.presentation.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapan.aetherai.presentation.onboarding.page.*

@Composable
fun OnboardingScreen() {

    var page by remember {
        mutableIntStateOf(0)
    }
    var isUserInfoValid by remember {
        mutableStateOf(false)
    }
    var isTraitSelectionValid by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.weight(1f)
        ) {

            when(page) {

                0 -> WelcomePage()

                1 -> UserInfoPage(
                    onValidationChanged = {
                        isUserInfoValid = it
                    }
                )

                2 -> TraitSelectionPage(
                    onValidationChanged = {
                        isTraitSelectionValid = it
                    }
                )
            }
        }

        Button(
            onClick = {
                if(page < 2) page++
            },
            enabled = when(page) {

                0 -> true

                1 -> isUserInfoValid

                2 -> isTraitSelectionValid

                else -> false
            }
        ) {
            Text(
                text = when(page) {
                    2 -> "Finish"
                    else -> "Next"
                }
            )
        }
    }
}
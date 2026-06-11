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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        when(page) {

            0 -> WelcomePage()

            1 -> UserInfoPage()

            2 -> TraitSelectionPage()
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if(page < 2) page++
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Next")
        }
    }
}
package com.tapan.aetherai.presentation.onboarding.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val valueProps = listOf(
    "Private by Design",
    "Offline First Experience",
    "Personalized Intelligence"
)

@Composable
fun WelcomePage() {

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentIndex = (currentIndex + 1) % valueProps.size
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "AetherAI",
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = valueProps[currentIndex],
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
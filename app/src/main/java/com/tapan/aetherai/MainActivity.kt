package com.tapan.aetherai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tapan.aetherai.core.designsystem.theme.AetherTheme
import com.tapan.aetherai.presentation.navigation.AetherNavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AetherTheme {
                AetherNavGraph()
            }
        }
    }
}
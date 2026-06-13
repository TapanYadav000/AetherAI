package com.tapan.aetherai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.tapan.aetherai.core.designsystem.theme.AetherTheme
import com.tapan.aetherai.presentation.navigation.AetherNavGraph
import com.tapan.aetherai.ui.theme.AetherAITheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            var darkTheme by rememberSaveable {
                mutableStateOf(false)
            }

            AetherAITheme(
                darkTheme = darkTheme
            ) {

                AetherNavGraph(
                    darkTheme = darkTheme,
                    onThemeToggle = {
                        darkTheme = !darkTheme
                    }
                )
            }
        }
    }
}
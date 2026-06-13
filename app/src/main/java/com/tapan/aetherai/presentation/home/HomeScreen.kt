package com.tapan.aetherai.presentation.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tapan.aetherai.presentation.chat.ChatHistoryScreen
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface

@Composable
fun HomeScreen(
    darkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onEditProfileClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val amplitude by viewModel.amplitude.collectAsStateWithLifecycle()
    var showInputPanel = remember {
        mutableStateOf(false)
    }

    var message = remember {
        mutableStateOf("")
    }

    var contentMode = remember {
        mutableStateOf(
            HomeContentMode.AURA
        )
    }

    val transitionProgress = animateFloatAsState(
        targetValue =
            if (contentMode.value ==
                HomeContentMode.CHAT_HISTORY
            ) 1f else 0f,
        label = "home_transition"
    )

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {
                viewModel.startListening()
            }
        }

    if (uiState.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    val profile = uiState.profile ?: return

    if (contentMode.value == HomeContentMode.AURA) {
        Surface(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(
                WindowInsets.statusBars
            ),
            color = MaterialTheme.colorScheme.background
        ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .graphicsLayer {

                    alpha =
                        1f - transitionProgress.value

                    translationY =
                        -200f *
                                transitionProgress.value
                }
                .pointerInput(Unit) {

                    detectVerticalDragGestures {

                            _, dragAmount ->

                        if (dragAmount < -20f) {

                            contentMode.value =
                                HomeContentMode.CHAT_HISTORY
                        }
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Good Evening, ${profile.name}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "How can I help today?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            Box(
                modifier = Modifier.size(280.dp),
                contentAlignment = Alignment.Center
            ) {
                AuraCircle(
                    state = if (amplitude > 0f) {
                        AuraState.Listening(amplitude)
                    } else {
                        AuraState.Idle
                    }
                )
            }


            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                FilledIconButton(
                    onClick = {
                        permissionLauncher.launch(
                            Manifest.permission.RECORD_AUDIO
                        )
                    }
                ) {
                    Icon(
                        Icons.Default.Mic,
                        contentDescription = null
                    )
                }

                FilledIconButton(
                    onClick = {
                        viewModel.stopListening()
                    }
                ) {
                    Icon(
                        Icons.Default.Stop,
                        contentDescription = null
                    )
                }

                FilledIconButton(
                    onClick = onThemeToggle
                ) {
                    Icon(
                        imageVector =
                            if (darkTheme)
                                Icons.Default.LightMode
                            else
                                Icons.Default.DarkMode,
                        contentDescription = "Theme"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )



            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                tonalElevation = 4.dp
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = message.value,
                        onValueChange = {
                            message.value = it
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text("Ask Aether AI...")
                        }
                    )

                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            IconButton(
                onClick = onEditProfileClick
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null
                )
            }

            AnimatedVisibility(
                visible = showInputPanel.value,
                enter = slideInVertically {
                    it
                },
                exit = slideOutVertically {
                    it
                }
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        OutlinedTextField(
                            value = message.value,
                            onValueChange = {
                                message.value = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Type a message")
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Button(
                            onClick = {

                            }
                        ) {
                            Text("Send")
                        }
                    }
                }
            }
        }
    }
}

    AnimatedVisibility(
        visible =
            contentMode.value ==
                    HomeContentMode.CHAT_HISTORY,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Column {


            ChatHistoryScreen(
                onBackClick = {
                    contentMode.value =
                        HomeContentMode.AURA
                }
            )
        }
    }
}
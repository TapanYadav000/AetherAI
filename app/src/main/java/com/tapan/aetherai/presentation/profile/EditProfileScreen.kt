package com.tapan.aetherai.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tapan.aetherai.ui.theme.SuccessGreen

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

        return
    }
    Surface(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(
            WindowInsets.statusBars
        ),
        color = MaterialTheme.colorScheme.background
    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp).windowInsetsPadding(
                WindowInsets.statusBars
            )
    ) {

        Text(
            text = "Edit Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.updateName(it)
            },
            label = {
                Text("Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.age,
            onValueChange = {
                viewModel.updateAge(it)
            },
            label = {
                Text("Age")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.saveProfile()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        AnimatedVisibility(
            visible = state.isSaved
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "✓",
                        color = SuccessGreen,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text = "Profile Updated Successfully",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
    }
}
package com.tapan.aetherai.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
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

        if (state.isSaved) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "✓ Profile Updated Successfully",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
package com.tapan.aetherai.presentation.onboarding.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapan.aetherai.presentation.onboarding.OnboardingState
import com.tapan.aetherai.presentation.onboarding.OnboardingViewModel

@Composable
fun UserInfoPage(
    state: OnboardingState,
    viewModel: OnboardingViewModel,
    onValidationChanged: (Boolean) -> Unit
) {


    var otpRequested by remember { mutableStateOf(false) }
    var otpVerified by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Tell us about yourself",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.name,
            onValueChange = viewModel::updateName,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.age,
            onValueChange = viewModel::updateAge,
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = viewModel::updatePhone,
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                otpRequested = true
            },
            enabled = state.phoneNumber.length == 10,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }

        AnimatedVisibility(
            visible = otpRequested
        ) {

            Column {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Mock OTP: 1234",
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.otp,
                    onValueChange = viewModel::updateOtp,
                    label = { Text("Enter OTP") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        viewModel.updateOtpVerified(
                            state.otp == "1234"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify OTP")
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (state.otpVerified) {

                    Text(
                        text = "✓ Phone Number Verified",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
    LaunchedEffect(state) {

        onValidationChanged(

            state.name.isNotBlank() &&
                    state.age.isNotBlank() &&
                    state.phoneNumber.length == 10 &&
                    state.otpVerified
        )
    }
}
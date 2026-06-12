package com.tapan.aetherai.presentation.onboarding.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserInfoPage(
    onValidationChanged: (Boolean) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

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
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                otpRequested = true
            },
            enabled = phone.length == 10,
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
                    value = otp,
                    onValueChange = { otp = it },
                    label = { Text("Enter OTP") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        otpVerified = otp == "1234"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify OTP")
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (otpVerified) {

                    Text(
                        text = "✓ Phone Number Verified",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
    LaunchedEffect(
        name,
        age,
        phone,
        otpVerified
    ) {
        onValidationChanged(
            name.isNotBlank() &&
                    age.isNotBlank() &&
                    phone.length == 10 &&
                    otpVerified
        )
    }
}
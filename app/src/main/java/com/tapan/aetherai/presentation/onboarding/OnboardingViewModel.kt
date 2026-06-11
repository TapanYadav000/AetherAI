package com.tapan.aetherai.presentation.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())

    val state: StateFlow<OnboardingState> =
        _state.asStateFlow()
}
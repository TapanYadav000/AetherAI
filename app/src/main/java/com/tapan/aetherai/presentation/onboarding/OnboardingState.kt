package com.tapan.aetherai.presentation.onboarding

import com.tapan.aetherai.domain.model.PersonalityTrait

data class OnboardingState(

    val currentPage: Int = 0,

    val name: String = "",

    val age: String = "",

    val phoneNumber: String = "",

    val otp: String = "",

    val otpVerified: Boolean = false,

    val selectedTraits: Set<PersonalityTrait> = emptySet(),

    val isLoading: Boolean = false,

    val isCompleted: Boolean = false
)
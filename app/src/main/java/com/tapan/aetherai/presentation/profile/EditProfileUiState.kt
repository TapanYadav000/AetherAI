package com.tapan.aetherai.presentation.profile

data class EditProfileUiState(

    val name: String = "",

    val age: String = "",

    val isLoading: Boolean = true,

    val isSaved: Boolean = false
)
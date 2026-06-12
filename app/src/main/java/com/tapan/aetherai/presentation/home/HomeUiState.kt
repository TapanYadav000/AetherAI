package com.tapan.aetherai.presentation.home

import com.tapan.aetherai.domain.model.UserProfile

data class HomeUiState(
    val profile: UserProfile? = null,
    val isLoading: Boolean = true,

)
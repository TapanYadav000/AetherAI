package com.tapan.aetherai.presentation.chat

data class ChatUiState(
    val input: String = "",
    val isSending: Boolean = false,
    val error: String? = null
)
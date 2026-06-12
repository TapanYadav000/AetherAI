package com.tapan.aetherai.presentation.chat


sealed class MessageProcessingState {

    data object Idle : MessageProcessingState()

    data object Typing : MessageProcessingState()

    data object Validating : MessageProcessingState()

    data object Processing : MessageProcessingState()

    data object Responding : MessageProcessingState()

    data class Error(
        val message: String
    ) : MessageProcessingState()
}
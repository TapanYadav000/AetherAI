package com.tapan.aetherai.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapan.aetherai.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.CancellationException

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _processingState =
        MutableStateFlow<MessageProcessingState>(
            MessageProcessingState.Idle
        )

    val processingState =
        _processingState.asStateFlow()

    private var currentJob: Job? = null
    fun sendMessage(
        message: String
    ) {

        currentJob?.cancel()

        _processingState.value =
            MessageProcessingState.Idle

        currentJob = viewModelScope.launch {

            _processingState.value =
                MessageProcessingState.Typing

            delay(300)

            _processingState.value =
                MessageProcessingState.Validating

            delay(300)

            if (message.isBlank()) {

                _processingState.value =
                    MessageProcessingState.Error(
                        "Message cannot be empty"
                    )

                return@launch
            }

            _processingState.value =
                MessageProcessingState.Processing

            try {

                withTimeout(8_000) {

                    delay(1000)
                }

            }  catch (_: CancellationException) {

            return@launch

        } catch (_: Exception) {

            _processingState.value =
                MessageProcessingState.Error(
                    "Processing timeout"
                )

            return@launch
        }

            _processingState.value =
                MessageProcessingState.Responding

            delay(500)

            _processingState.value =
                MessageProcessingState.Idle
        }
    }
}
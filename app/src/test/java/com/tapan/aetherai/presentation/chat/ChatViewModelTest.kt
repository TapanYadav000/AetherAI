package com.tapan.aetherai.presentation.chat

import com.tapan.aetherai.domain.repository.ChatRepository
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {


        @Test
        fun happyPathTransition() = runTest {

            val repository = mockk<ChatRepository>(
                relaxed = true
            )

            val viewModel =
                ChatViewModel(repository)

            viewModel.sendMessage("Hello")

            advanceUntilIdle()

            println(viewModel.processingState.value)
        }
    @Test
    fun cancellationMidFlow() = runTest {

        val repository = mockk<ChatRepository>(
            relaxed = true
        )

        val viewModel = ChatViewModel(repository)

        viewModel.sendMessage("First")

        viewModel.sendMessage("Second")

        assert(
            viewModel.processingState.value
                    is MessageProcessingState.Typing
        )
    }
    @Test
    fun timeoutStateExists() {

        val errorState =
            MessageProcessingState.Error(
                "Processing timeout"
            )

        assertEquals(
            "Processing timeout",
            errorState.message
        )
    }
}
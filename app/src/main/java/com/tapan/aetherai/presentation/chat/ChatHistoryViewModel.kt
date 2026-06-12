package com.tapan.aetherai.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tapan.aetherai.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatHistoryViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    val messages =
        chatRepository
            .getPagedMessages()
            .cachedIn(viewModelScope)
}
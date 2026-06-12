package com.tapan.aetherai.domain.repository

import androidx.paging.PagingData
import com.tapan.aetherai.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getMessages(): Flow<List<ChatMessage>>

    suspend fun saveMessage(message: ChatMessage)

    suspend fun deleteMessage(id: String)

    fun getPagedMessages():
            Flow<PagingData<ChatMessage>>

    suspend fun insertMessage(
        message: ChatMessage
    )
}
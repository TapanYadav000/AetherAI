package com.tapan.aetherai.domain.repository

import com.tapan.aetherai.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getMessages(): Flow<List<ChatMessage>>

    suspend fun saveMessage(message: ChatMessage)

    suspend fun deleteMessage(id: String)
}
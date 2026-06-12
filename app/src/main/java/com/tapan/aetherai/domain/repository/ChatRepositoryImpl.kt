package com.tapan.aetherai.domain.repository

import androidx.paging.*
import com.tapan.aetherai.data.local.dao.ChatMessageDao
import com.tapan.aetherai.data.local.entity.ChatMessageEntity
import com.tapan.aetherai.data.mapper.toDomain
import com.tapan.aetherai.data.mapper.toEntity
import com.tapan.aetherai.domain.model.ChatMessage
import com.tapan.aetherai.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatMessageDao: ChatMessageDao
) : ChatRepository {

    override fun getMessages():
            Flow<List<ChatMessage>> {

        return chatMessageDao.observeMessages()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun saveMessage(
        message: ChatMessage
    ) {

        chatMessageDao.insertMessage(
            message.toEntity()
        )
    }

    override suspend fun deleteMessage(
        id: String
    ) {

        chatMessageDao.deleteMessage(id)
    }

    override fun getPagedMessages():
            Flow<PagingData<ChatMessage>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ) {
            chatMessageDao.getMessagesPaged()
        }.flow.map { pagingData ->

            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun insertMessage(
        message: ChatMessage
    ) {
        saveMessage(message)
    }
}
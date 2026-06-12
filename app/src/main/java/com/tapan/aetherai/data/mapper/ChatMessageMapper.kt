package com.tapan.aetherai.data.mapper

import com.tapan.aetherai.data.local.entity.ChatMessageEntity
import com.tapan.aetherai.domain.model.ChatMessage
import com.tapan.aetherai.domain.model.MessageSender

fun ChatMessageEntity.toDomain(): ChatMessage {

    return ChatMessage(
        id = id,
        sender = MessageSender.valueOf(sender),
        message = message,
        timestamp = timestamp,
        meta = meta,
        updatedAt = updatedAt,
        lastSyncedAt = lastSyncedAt
    )
}

fun ChatMessage.toEntity(): ChatMessageEntity {

    return ChatMessageEntity(
        id = id,
        sender = sender.name,
        message = message,
        timestamp = timestamp,
        meta = meta,
        updatedAt = updatedAt,
        lastSyncedAt = lastSyncedAt
    )
}
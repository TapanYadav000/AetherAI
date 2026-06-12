package com.tapan.aetherai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tapan.aetherai.domain.model.MessageMeta

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(

    @PrimaryKey
    val id: String,

    val sender: String,

    val message: String,

    val timestamp: Long,

    val meta: MessageMeta,

    val updatedAt: Long,

    val lastSyncedAt: Long?
)
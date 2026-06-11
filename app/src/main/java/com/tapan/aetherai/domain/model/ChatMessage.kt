package com.tapan.aetherai.domain.model

data class ChatMessage(
    val id: String,
    val sender: MessageSender,
    val message: String,
    val timestamp: Long,
    val meta: MessageMeta,
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncedAt: Long? = null
)
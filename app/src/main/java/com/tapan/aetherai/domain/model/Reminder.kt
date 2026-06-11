package com.tapan.aetherai.domain.model

data class Reminder(
    val id: String,
    val title: String,
    val description: String,
    val scheduledTime: Long,
    val isCompleted: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncedAt: Long? = null
)
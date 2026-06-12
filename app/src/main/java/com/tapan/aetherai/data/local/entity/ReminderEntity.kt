package com.tapan.aetherai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val description: String,

    val scheduledTime: Long,

    val isCompleted: Boolean,

    val updatedAt: Long,

    val lastSyncedAt: Long?
)
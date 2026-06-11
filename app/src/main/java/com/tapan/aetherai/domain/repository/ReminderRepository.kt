package com.tapan.aetherai.domain.repository

import com.tapan.aetherai.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    fun getReminders(): Flow<List<Reminder>>

    suspend fun saveReminder(reminder: Reminder)

    suspend fun deleteReminder(id: String)
}
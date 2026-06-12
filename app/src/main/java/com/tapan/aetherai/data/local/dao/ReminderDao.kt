package com.tapan.aetherai.data.local.dao

import androidx.room.*
import com.tapan.aetherai.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(
        reminder: ReminderEntity
    )

    @Update
    suspend fun updateReminder(
        reminder: ReminderEntity
    )

    @Delete
    suspend fun deleteReminder(
        reminder: ReminderEntity
    )

    @Query(
        """
        SELECT * FROM reminders
        ORDER BY scheduledTime ASC
        """
    )
    fun observeReminders():
            Flow<List<ReminderEntity>>
}
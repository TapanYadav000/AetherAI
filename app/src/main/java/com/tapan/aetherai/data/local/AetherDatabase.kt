package com.tapan.aetherai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tapan.aetherai.data.local.converter.MessageMetaConverter
import com.tapan.aetherai.data.local.dao.ChatMessageDao
import com.tapan.aetherai.data.local.dao.ReminderDao
import com.tapan.aetherai.data.local.entity.ChatMessageEntity
import com.tapan.aetherai.data.local.entity.ReminderEntity

@Database(
    entities = [
        ChatMessageEntity::class,
        ReminderEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MessageMetaConverter::class
)
abstract class AetherDatabase : RoomDatabase() {

    abstract fun chatMessageDao(): ChatMessageDao

    abstract fun reminderDao(): ReminderDao
}
package com.tapan.aetherai.di

import android.content.Context
import androidx.room.Room
import com.tapan.aetherai.data.local.AetherDatabase
import com.tapan.aetherai.data.local.dao.ChatMessageDao
import com.tapan.aetherai.data.local.dao.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AetherDatabase {

        return Room.databaseBuilder(
            context,
            AetherDatabase::class.java,
            "aether_database"
        ).build()
    }

    @Provides
    fun provideChatMessageDao(
        database: AetherDatabase
    ): ChatMessageDao {
        return database.chatMessageDao()
    }

    @Provides
    fun provideReminderDao(
        database: AetherDatabase
    ): ReminderDao {
        return database.reminderDao()
    }
}
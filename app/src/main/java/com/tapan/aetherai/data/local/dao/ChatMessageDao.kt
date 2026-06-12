package com.tapan.aetherai.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.tapan.aetherai.data.local.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(
        message: ChatMessageEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(
        messages: List<ChatMessageEntity>
    )

    @Query(
        """
        SELECT * FROM chat_messages
        ORDER BY timestamp DESC
        """
    )
    fun getMessagesPaged():
            PagingSource<Int, ChatMessageEntity>

    @Query(
        """
        SELECT * FROM chat_messages
        ORDER BY timestamp DESC
        """
    )
    fun observeMessages():
            Flow<List<ChatMessageEntity>>

    @Query(
        """
        DELETE FROM chat_messages
        WHERE id = :id
        """
    )
    suspend fun deleteMessage(
        id: String
    )
}
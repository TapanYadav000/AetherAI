package com.tapan.aetherai.domain.repository

import com.tapan.aetherai.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUserProfile(
        profile: UserProfile
    )

    fun getUserProfile(): Flow<UserProfile>
}
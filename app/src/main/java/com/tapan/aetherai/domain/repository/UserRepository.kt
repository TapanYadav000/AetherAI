package com.tapan.aetherai.domain.repository

import com.tapan.aetherai.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUserProfile(
        profile: UserProfile
    )

    suspend fun updateUserProfile(
        name: String,
        age: Int
    )

    fun getUserProfile(): Flow<UserProfile>
}
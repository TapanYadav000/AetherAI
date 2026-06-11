package com.tapan.aetherai.domain.repository

import com.tapan.aetherai.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserProfile(): Flow<UserProfile?>

    suspend fun saveUserProfile(profile: UserProfile)

    suspend fun clearUserProfile()
}
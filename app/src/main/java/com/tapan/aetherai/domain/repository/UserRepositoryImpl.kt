package com.tapan.aetherai.data.repository

import com.tapan.aetherai.data.datastore.UserPreferencesDataStore
import com.tapan.aetherai.domain.model.UserProfile
import com.tapan.aetherai.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataStore: UserPreferencesDataStore
) : UserRepository {

    override suspend fun saveUserProfile(
        profile: UserProfile
    ) {
        dataStore.saveUserProfile(profile)
    }

    override fun getUserProfile(): Flow<UserProfile> {
        return dataStore.getUserProfile()
    }

    override suspend fun updateUserProfile(
        name: String,
        age: Int
    ) {
        dataStore.updateUserProfile(
            name = name,
            age = age
        )
    }
}
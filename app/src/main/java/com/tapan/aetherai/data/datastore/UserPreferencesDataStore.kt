package com.tapan.aetherai.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tapan.aetherai.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray

private val Context.userDataStore by preferencesDataStore(
    name = "aether_user_preferences"
)

class UserPreferencesDataStore(
    private val context: Context
) {

    companion object {

        private val NAME =
            stringPreferencesKey("name")

        private val AGE =
            stringPreferencesKey("age")

        private val PHONE =
            stringPreferencesKey("phone")

        private val TRAITS =
            stringPreferencesKey("traits")
    }

    suspend fun saveUserProfile(
        profile: UserProfile
    ) {

        context.userDataStore.edit { preferences ->

            preferences[NAME] = profile.name

            preferences[AGE] =
                profile.age.toString()

            preferences[PHONE] =
                profile.phoneNumber

            preferences[TRAITS] =
                JSONArray(
                    profile.personalityTraits.map {
                        it.name
                    }
                ).toString()
        }
    }

    fun getUserProfile(): Flow<UserProfile> {

        return context.userDataStore.data.map { preferences ->

            val traitsJson =
                preferences[TRAITS] ?: "[]"

            val traitsArray =
                JSONArray(traitsJson)

            val traits =
                mutableListOf<com.tapan.aetherai.domain.model.PersonalityTrait>()

            for (i in 0 until traitsArray.length()) {

                traits.add(
                    com.tapan.aetherai.domain.model.PersonalityTrait.valueOf(
                        traitsArray.getString(i)
                    )
                )
            }

            UserProfile(
                name = preferences[NAME] ?: "",
                age = preferences[AGE]?.toIntOrNull() ?: 0,
                phoneNumber = preferences[PHONE] ?: "",
                personalityTraits = traits
            )
        }
    }
}
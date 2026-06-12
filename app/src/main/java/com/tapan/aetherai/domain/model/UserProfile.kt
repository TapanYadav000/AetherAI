package com.tapan.aetherai.domain.model

data class UserProfile(

    val id: String = "",

    val name: String = "",

    val age: Int = 0,

    val phoneNumber: String = "",

    val personalityTraits: List<PersonalityTrait> = emptyList(),

    val createdAt: Long = System.currentTimeMillis(),

    val updatedAt: Long = System.currentTimeMillis(),

    val lastSyncedAt: Long? = null
)
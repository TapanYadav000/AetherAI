package com.tapan.aetherai.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageMeta(
    val isEdited: Boolean = false,
    val retryCount: Int = 0,
    val sentiment: String? = null
)

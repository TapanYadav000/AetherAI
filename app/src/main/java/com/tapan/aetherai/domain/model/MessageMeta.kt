package com.tapan.aetherai.domain.model

data class MessageMeta(
    val isEdited: Boolean = false,
    val retryCount: Int = 0,
    val sentiment: String? = null
)
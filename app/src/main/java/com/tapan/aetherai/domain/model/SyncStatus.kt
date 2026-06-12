package com.tapan.aetherai.domain.model

sealed class SyncStatus {

    data object Idle : SyncStatus()

    data object Syncing : SyncStatus()

    data object Success : SyncStatus()

    data class Error(
        val message: String
    ) : SyncStatus()
}
package com.tapan.aetherai.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.tapan.aetherai.domain.model.SyncStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncManager @Inject constructor(
    private val context: Context
) {

    private val _syncStatus =
        MutableStateFlow<SyncStatus>(
            SyncStatus.Idle
        )

    val syncStatus =
        _syncStatus.asStateFlow()

    fun startSync() {

        _syncStatus.value =
            SyncStatus.Syncing

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(
                NetworkType.CONNECTED
            )
            .build()

        val syncRequest =
            PeriodicWorkRequestBuilder<SyncWorker>(
                15,
                TimeUnit.MINUTES
            )
                .setConstraints(
                    constraints
                )
                .build()

        WorkManager.getInstance(
            context
        ).enqueueUniquePeriodicWork(
            "aether_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )

        _syncStatus.value =
            SyncStatus.Success
    }
}
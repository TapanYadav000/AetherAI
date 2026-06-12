package com.tapan.aetherai.sync


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(
    context,
    params
) {

    override suspend fun doWork(): Result {

        return try {

            // TODO:
            // Sync only changed rows
            // Local wins on conflict

            Result.success()

        } catch (e: Exception) {

            Result.retry()
        }
    }
}
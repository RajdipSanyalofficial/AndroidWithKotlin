package com.example.workmanagerforbgtasks

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        return try {
            Log.d("WorkManager", "Work is being done!")
            // Simulate a task (e.g., network operation)
            Result.success() // Indicate success
        } catch (exception: Exception) {
            Log.e("WorkManager", "Work failed")
            Result.retry() // Request retry on failure
        }
    }
}

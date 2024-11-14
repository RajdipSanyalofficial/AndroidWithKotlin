package com.example.chainedworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.util.Log

class UploadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val processedFilePath = inputData.getString("PROCESSED_FILE_PATH") ?: return Result.failure()

        return try {
            // Simulate file upload
            Thread.sleep(2000)

            Log.d("UploadWorker", "File uploaded: $processedFilePath")
            Result.success()
        } catch (e: Exception) {
            Log.e("UploadWorker", "File upload failed", e)
            Result.failure()
        }
    }
}

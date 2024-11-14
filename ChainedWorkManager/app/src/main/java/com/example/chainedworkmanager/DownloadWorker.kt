package com.example.chainedworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data
import android.util.Log

class DownloadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            // Simulate file download
            Thread.sleep(2000)
            val filePath = "/path/to/downloaded/file"

            // Pass file path to the next worker
            val outputData = Data.Builder()
                .putString("FILE_PATH", filePath)
                .build()

            Log.d("DownloadWorker", "File downloaded: $filePath")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e("DownloadWorker", "File download failed", e)
            Result.failure()
        }
    }
}

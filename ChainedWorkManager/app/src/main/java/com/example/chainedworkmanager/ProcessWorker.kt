package com.example.chainedworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data
import android.util.Log

class ProcessWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val filePath = inputData.getString("FILE_PATH") ?: return Result.failure()

        return try {
            // Simulate file processing
            Thread.sleep(2000)
            val processedFilePath = "$filePath.processed"

            // Pass processed file path to the next worker
            val outputData = Data.Builder()
                .putString("PROCESSED_FILE_PATH", processedFilePath)
                .build()

            Log.d("ProcessWorker", "File processed: $processedFilePath")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e("ProcessWorker", "File processing failed", e)
            Result.failure()
        }
    }
}

package com.example.smarthomecontrollerapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class DeviceSyncWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // Sync device states with server
        return Result.success()
    }
}
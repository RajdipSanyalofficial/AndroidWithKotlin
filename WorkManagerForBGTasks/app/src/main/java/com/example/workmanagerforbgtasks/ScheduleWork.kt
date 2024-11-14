package com.example.workmanagerforbgtasks

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager

fun ScheduleWork(context: Context) {
    // Define constraints
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED) // Requires internet
        .setRequiresCharging(true) // Requires device to be charging
        .build()

    // Create a One-time WorkRequest
    val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
        .setConstraints(constraints) // Apply constraints
        .build()

    // Enqueue the work
    WorkManager.getInstance(context).enqueue(workRequest)

    // Observe the work status
    WorkManager.getInstance(context).getWorkInfoByIdLiveData(workRequest.id)
        .observeForever { workInfo ->
            if (workInfo != null) {
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> Log.d("WorkManager", "Work enqueued")
                    WorkInfo.State.RUNNING -> Log.d("WorkManager", "Work running")
                    WorkInfo.State.SUCCEEDED -> Log.d("WorkManager", "Work succeeded")
                    WorkInfo.State.FAILED -> Log.d("WorkManager", "Work failed")
                    WorkInfo.State.CANCELLED -> Log.d("WorkManager","Work cancelled")
                    WorkInfo.State.BLOCKED -> Log.d("WorkManager","Work blocked")

                }
            }
        }
}

package com.example.schedulingbgtaskwithworkmanager

import android.util.Log
import androidx.work.WorkManager
import androidx.work.WorkInfo

fun CheckWorkStatus() {
    WorkManager.getInstance(this).getWorkInfosForUniqueWorkLiveData("FetchDataWorker").observe(this) { workInfoList ->
        workInfoList.forEach { workInfo ->
            val status = workInfo.state.name
            Log.d("WorkStatus", "Work status: $status")
        }
    }
}
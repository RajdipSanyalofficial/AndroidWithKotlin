package com.example.chainedworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.workmanagerchain.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startChainButton.setOnClickListener {
            startWorkChain()
        }
    }

    private fun startWorkChain() {
        // First task: Download file
        val downloadWorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .build()

        // Second task: Process the downloaded file
        val processWorkRequest = OneTimeWorkRequestBuilder<ProcessWorker>()
            .build()

        // Third task: Upload the processed file
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .build()

        // Chain the tasks
        WorkManager.getInstance(this)
            .beginWith(downloadWorkRequest)
            .then(processWorkRequest)
            .then(uploadWorkRequest)
            .enqueue()

        // Observe the final task's status (optional)
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    val success = workInfo.state == WorkInfo.State.SUCCEEDED
                    binding.statusTextView.text = if (success) {
                        "File upload completed successfully."
                    } else {
                        "File upload failed."
                    }
                }
            }
    }
}

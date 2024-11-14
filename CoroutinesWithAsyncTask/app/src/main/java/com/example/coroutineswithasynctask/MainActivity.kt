package com.example.coroutineswithasynctask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() = runBlocking {

            val apiService = RetrofitClient.instance.create(ApiService::class.java)

            // Launching a coroutine to fetch data
            val dataDeferred = async {
                apiService.fetchData()
            }

            // Doing other tasks in the meantime
            println("Fetching data in background...")

            // Awaiting and printing the fetched data
            try {
            val data = dataDeferred.await()
            println("Data fetched: $data")
            }
            catch (e: Exception) {
            println("Error fetching data: ${e.message}")
            }
    }
            override fun onDestroy() {
            super.onDestroy()
                activityScope.cancel() // Cancel coroutines when activity is destroyed
    }

}




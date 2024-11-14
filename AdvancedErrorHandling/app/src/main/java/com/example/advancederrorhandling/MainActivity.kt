package com.example.advancederrorhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Start network request with retry logic
        fetchPostsWithRetry()
    }

    private fun fetchPostsWithRetry() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = retryNetworkCall(times = 3)
            resultTextView.text = result
        }
    }

    private suspend fun retryNetworkCall(times: Int): String {
        var currentAttempt = 0
        var delayTime = 1000L // Initial delay time of 1 second

        while (currentAttempt < times) {
            try {
                val response = withContext(Dispatchers.IO) { makeNetworkRequest() }
                if (response.isSuccessful) {
                    return "Success: ${response.body()?.size} posts fetched."
                } else {
                    return "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: IOException) {
                currentAttempt++
                if (currentAttempt == times) {
                    return "Network failed after $times attempts: ${e.message}"
                }
                delay(delayTime)
                delayTime *= 2 // Exponential backoff
            }
        }
        return "Unexpected error."
    }

    private suspend fun makeNetworkRequest(): Response<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }
}

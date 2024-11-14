package com.example.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // Launch a coroutine in the Main thread to fetch data
        CoroutineScope(Dispatchers.Main).launch {
            fetchData()
        }
    }

    // Function to fetch data using coroutines
    private suspend fun fetchData() {
        try {
            // Show a loading message while fetching data
            textView.text = "Fetching data..."

            // Fetch the data in the background thread
            val dataList = withContext(Dispatchers.IO) {
                apiService.fetchData()
            }

            // Update the UI with the fetched data
            val displayText = dataList.joinToString(separator = "\n") { "ID: ${it.id}, Name: ${it.name}, Email: ${it.email}" }
            textView.text = "Data fetched:\n$displayText"
        } catch (e: HttpException) {
            textView.text = "Error: ${e.message}"
        } catch (e: Exception) {
            textView.text = "An error occurred: ${e.message}"
        }
    }
}
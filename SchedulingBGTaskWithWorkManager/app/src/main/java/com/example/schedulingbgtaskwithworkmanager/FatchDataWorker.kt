package com.example.schedulingbgtaskwithworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FetchDataWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    interface ApiService {
        @GET("mock/data")
        suspend fun fetchData(): ApiResponse
    }

    data class ApiResponse(val data: String)

    override suspend fun doWork(): Result {
        return try {
            val response = createApiService().fetchData()
            saveDataToPreferences(response.data)
            Log.d("FetchDataWorker", "Data fetched and saved: ${response.data}")
            Result.success()
        } catch (e: Exception) {
            Log.e("FetchDataWorker", "Error fetching data: ${e.message}")
            Result.retry() // Retry on failure
        }
    }

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mockapi.io/") // Replace with your mock API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun saveDataToPreferences(data: String) {
        val sharedPreferences = applicationContext.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("fetchedData", data).apply()
    }
}
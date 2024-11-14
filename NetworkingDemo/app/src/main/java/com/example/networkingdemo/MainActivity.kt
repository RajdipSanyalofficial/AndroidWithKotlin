package com.example.networkingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the API service
        apiService = RetrofitClient.instance.create(ApiService::class.java)

        // Make the network request
        fetchUsers()
    }

    private fun fetchUsers() {
        val call = apiService.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    users?.let {
                        for (user in it) {
                            Log.d("MainActivity", "User: ${user.name}, Email: ${user.email}")
                        }
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()} - ${response.message()}")
                    when (response.code()) {
                        400 -> Log.e("MainActivity", "Bad Request")
                        401 -> Log.e("MainActivity", "Unauthorized")
                        404 -> Log.e("MainActivity", "Not Found")
                        500 -> Log.e("MainActivity", "Internal Server Error")
                        else -> Log.e("MainActivity", "Unknown Error")
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                if (t is IOException) {
                    Log.e("MainActivity", "Network Failure: ${t.message}")
                } else {
                    Log.e("MainActivity", "Conversion Error: ${t.message}")
                }
            }
        })
    }
}

package com.example.coroutineswithasynctask

import retrofit2.http.GET

interface ApiService {
    @GET("data") // Replace with your actual endpoint
    suspend fun fetchData(): DataResponse
}
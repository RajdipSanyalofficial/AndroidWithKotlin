package com.example.coroutines

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun fetchData():List<DataResponse>
}
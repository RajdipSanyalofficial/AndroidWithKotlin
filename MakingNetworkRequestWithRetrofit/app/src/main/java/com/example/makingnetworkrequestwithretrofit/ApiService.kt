package com.example.makingnetworkrequestwithretrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): List<Repo>
}


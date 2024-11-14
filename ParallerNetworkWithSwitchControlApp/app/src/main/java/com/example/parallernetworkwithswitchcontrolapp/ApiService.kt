package com.example.parallernetworkwithswitchcontrolapp


import retrofit2.http.GET

interface ApiService {
    @GET("users/1")
    suspend fun getUser(): User

    @GET("users/1/posts")
    suspend fun getUserPosts(): List<Post>
}

data class User(val id: Int, val name: String, val username: String)
data class Post(val userId: Int, val id: Int, val title: String, val body: String)
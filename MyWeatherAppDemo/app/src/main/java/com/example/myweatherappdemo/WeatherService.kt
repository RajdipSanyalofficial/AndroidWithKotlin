package com.example.myweatherappdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Call<WeatherResponse>
}
package com.example.myweatherappdemo

data class WeatherResponse(
    val list: List<ForecastData>
)

data class ForecastData(
    val dtTxt: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double
)

data class Weather(
    val description: String,
    val icon: String
)
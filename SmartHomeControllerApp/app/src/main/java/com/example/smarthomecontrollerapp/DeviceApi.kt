package com.example.smarthomecontrollerapp

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface DeviceApi {

    // API to toggle the light (or any other device)
    @POST("/toggleDevice")
    suspend fun toggleDevice(@Query("device_id") deviceId: String): Response<String>

    // API to adjust thermostat temperature
    @POST("/adjustThermostat")
    suspend fun adjustThermostat(@Query("device_id") deviceId: String, @Query("temperature") temperature: Int): Response<String>

}
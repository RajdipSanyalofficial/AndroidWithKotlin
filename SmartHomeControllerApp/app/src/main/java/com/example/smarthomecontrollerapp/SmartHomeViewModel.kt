package com.example.smarthomecontrollerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthomecontrollerapp.DeviceApi
import kotlinx.coroutines.launch
import retrofit2.Response

class SmartHomeViewModel(private val apiService: DeviceApi) : ViewModel() {

    // LiveData for light status
    private val _lightStatus = MutableLiveData<String>()
    val lightStatus: LiveData<String> get() = _lightStatus

    // LiveData for thermostat status
    private val _thermostatStatus = MutableLiveData<String>()
    val thermostatStatus: LiveData<String> get() = _thermostatStatus

    companion object {
        const val TAG = "SmartHomeViewModel"
    }

    // Function to toggle light on/off
    fun toggleLight() {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Toggling light...")
                // Make network call to toggle the light
                val response: Response<String> = apiService.toggleDevice("light_id")

                if (response.isSuccessful) {
                    // Update the light status based on response
                    val result = response.body() ?: "Light toggled successfully"
                    _lightStatus.value = result
                    Log.d(TAG, "Light toggled successfully: $result")
                } else {
                    // Handle error response
                    _lightStatus.value = "Failed to toggle light"
                    Log.e(TAG, "Failed to toggle light: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                // Handle failure like network issues
                _lightStatus.value = "Error: ${e.message}"
                Log.e(TAG, "Error toggling light: ${e.message}", e)
            }
        }
    }

    // Function to adjust thermostat
    fun adjustThermostat(newTemperature: Int) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Adjusting thermostat to $newTemperature°C...")
                // Make network call to adjust the thermostat
                val response: Response<String> = apiService.adjustThermostat("thermostat_id", newTemperature)

                if (response.isSuccessful) {
                    // Update thermostat status with the new temperature
                    val result = "Thermostat set to $newTemperature°C"
                    _thermostatStatus.value = result
                    Log.d(TAG, result)
                } else {
                    // Handle error response
                    _thermostatStatus.value = "Failed to adjust thermostat"
                    Log.e(TAG, "Failed to adjust thermostat: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                // Handle failure like network issues
                _thermostatStatus.value = "Error: ${e.message}"
                Log.e(TAG, "Error adjusting thermostat: ${e.message}", e)
            }
        }
    }
}
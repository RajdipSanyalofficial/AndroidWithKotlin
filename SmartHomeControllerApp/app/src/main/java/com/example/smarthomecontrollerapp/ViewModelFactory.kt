package com.example.smarthomecontrollerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smarthomecontrollerapp.DeviceApi
import com.example.smarthomecontrollerapp.viewmodel.SmartHomeViewModel

class ViewModelFactory(private val apiService: DeviceApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SmartHomeViewModel::class.java)) {
            return SmartHomeViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
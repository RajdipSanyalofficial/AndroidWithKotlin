package com.example.smarthomecontrollerapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthomecontrollerapp.viewmodel.SmartHomeViewModel

class MainActivity : AppCompatActivity() {

    // Initialize the ViewModel
    private val viewModel: SmartHomeViewModel by viewModels { ViewModelFactory(NetworkModule.getApiService()) }

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        val buttonLight: Button = findViewById(R.id.button_light)
        val buttonThermostat: Button = findViewById(R.id.button_thermostat)
        val lightStatusTextView: TextView = findViewById(R.id.light_status)
        val thermostatStatusTextView: TextView = findViewById(R.id.thermostat_status)

        // Handle light toggle button click
        buttonLight.setOnClickListener {
            Log.d(TAG, "Light toggle button clicked")
            viewModel.toggleLight()
            Toast.makeText(this, "Toggling Light...", Toast.LENGTH_SHORT).show()
        }

        // Handle thermostat adjustment button click
        buttonThermostat.setOnClickListener {
            Log.d(TAG, "Thermostat adjustment button clicked")
            viewModel.adjustThermostat(24) // Example: Set thermostat to 24°C
            Toast.makeText(this, "Adjusting Thermostat...", Toast.LENGTH_SHORT).show()
        }

        // Observe light status LiveData to update the UI
        viewModel.lightStatus.observe(this) { status ->
            // Update the UI with the light status
            Log.d(TAG, "Light status updated: $status")
            lightStatusTextView.text = status
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        // Observe thermostat status LiveData to update the UI
        viewModel.thermostatStatus.observe(this) { status ->
            // Update the UI with the thermostat status
            Log.d(TAG, "Thermostat status updated: $status")
            thermostatStatusTextView.text = status
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }
    }
}
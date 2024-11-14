package com.example.dependencyinjectiondagger

import javax.inject.Inject

class AnalyticsService @Inject constructor() {
    fun logEvent(eventName: String) {
        // Logic to log event
        println("Logging event: $eventName")
    }
}
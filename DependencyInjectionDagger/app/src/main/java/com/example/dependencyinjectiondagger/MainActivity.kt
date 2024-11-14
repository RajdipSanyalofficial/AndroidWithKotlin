package com.example.dependencyinjectiondagger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var analyticsService: AnalyticsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use the injected AnalyticsService
        analyticsService.logEvent("MainActivity Created")

        // Set up button to navigate to UserFragment
        findViewById<Button>(R.id.btn_show_user).setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, UserFragment())
                addToBackStack(null) // Allow users to navigate back
            }
        }
    }
}

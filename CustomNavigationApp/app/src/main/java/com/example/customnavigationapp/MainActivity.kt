package com.example.customnavigationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.customnavigationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Check if user is logged in from SharedPreferences (or use LiveData)
        val isLoggedIn = checkUserLoggedIn()

        // Handle deep link logic
        if (intent?.data != null) {
            val deepLink = intent.data
            if (deepLink != null && deepLink.toString().contains("profile")) {
                if (!isLoggedIn) {
                    // If user is not logged in, navigate to LoginFragment
                    navController.navigate(R.id.loginFragment)
                } else {
                    // Navigate to ProfileFragment if logged in
                    navController.navigate(R.id.profileFragment, Bundle().apply {
                        putString("userId", deepLink.getQueryParameter("userId"))
                    })
                }
            }
        }
    }

    // Example function to check if user is logged in
    private fun checkUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean("is_logged_in", false)
    }
}
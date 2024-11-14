package com.example.uienhancementsanddatabindingdemo


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.uienhancementsanddatabindingdemo.adapter.UserAdapter
import com.example.uienhancementsanddatabindingdemo.databinding.ActivityMainBinding
import com.example.uienhancementsanddatabindingdemo.model.User
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = listOf(
            User("Rajdip Sanyal", 26),
            User("Pritam Karmakar", 25),
            User("Soumadeep Roy", 27),
            User("Avik Dey", 29)
        )

        val adapter = UserAdapter(users)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =adapter
    }
}
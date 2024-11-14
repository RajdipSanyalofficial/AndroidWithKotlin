package com.example.parallernetworkwithswitchcontrolapp;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parallernetworkwithswitchcontrolapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserPostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchUserData()
    }

    private fun fetchUserData() {
        lifecycleScope.launch {
            val userDeferred = async { fetchUser() }
            val postsDeferred = async { fetchUserPosts() }

            val user = userDeferred.await()
            val posts = postsDeferred.await()

            if (user != null && posts != null) {
                val items = posts.map { post -> Pair(user, post) }
                adapter = UserPostsAdapter(items)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    private suspend fun fetchUser(): User? {
        return try {
            withContext(Dispatchers.IO) {
                RetrofitInstance.api.getUser()
            }
        } catch (e: Exception) {
            showError("Failed to fetch user: ${e.message}")
            null
        }
    }

    private suspend fun fetchUserPosts(): List<Post>? {
        return try {
            withContext(Dispatchers.IO) {
                RetrofitInstance.api.getUserPosts()
            }
        } catch (e: Exception) {
            showError("Failed to fetch posts: ${e.message}")
            null
        }
    }

    private fun showError(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
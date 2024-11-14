package com.example.firebaseintegration

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    // Bind UI elements
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var birthYearEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var firestoreDataTextView: TextView
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var addUserButton: Button
    private lateinit var getUsersButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Bind UI elements
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        birthYearEditText = findViewById(R.id.birthYearEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        firestoreDataTextView = findViewById(R.id.firestoreDataTextView)
        signInButton = findViewById(R.id.signInButton)
        signUpButton = findViewById(R.id.signUpButton)
        addUserButton = findViewById(R.id.addUserButton)
        getUsersButton = findViewById(R.id.getUsersButton)

        // Set click listeners
        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signInUser(email, password)
        }

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signUpUser(email, password)
        }

        addUserButton.setOnClickListener {
            addUser()
        }

        getUsersButton.setOnClickListener {
            getUsers()
        }
    }

    // Firebase Authentication: Sign In
    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("Auth", "Sign in successful: ${user?.email}")
                    Toast.makeText(this, "Welcome back, ${user?.email}!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("Auth", "Sign in failed", task.exception)
                    Toast.makeText(this, "Sign in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Firebase Authentication: Sign Up
    private fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("Auth", "Registration successful: ${user?.email}")
                    Toast.makeText(this, "Registration successful! Welcome, ${user?.email}!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("Auth", "Registration failed", task.exception)
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Firestore: Add a User
    private fun addUser() {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val birthYear = birthYearEditText.text.toString().toIntOrNull()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && birthYear != null) {
            val user = hashMapOf(
                "first" to firstName,
                "last" to lastName,
                "born" to birthYear
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("Firestore", "Document added with ID: ${documentReference.id}")
                    Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error adding document", e)
                    Toast.makeText(this, "Error adding user: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
        }
    }

    // Firestore: Get All Users
    private fun getUsers() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val sb = StringBuilder()
                for (document in result) {
                    val firstName = document.getString("first") ?: "No first name"
                    val lastName = document.getString("last") ?: "No last name"
                    val born = document.getLong("born")?.toInt() ?: "No year of birth"
                    sb.append("ID: ${document.id}, First: $firstName, Last: $lastName, Born: $born\n")
                }
                firestoreDataTextView.text = sb.toString()
                Toast.makeText(this, "Users retrieved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error retrieving documents", e)
                Toast.makeText(this, "Error retrieving users: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
package com.example.daisy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth  // Declare firebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        val profileName: TextView = findViewById(R.id.profile_name)
        val profileBio: TextView = findViewById(R.id.profile_bio)
        val editButton: Button = findViewById(R.id.edit_button)
        val logoutButton: Button = findViewById(R.id.logout_button)



        // Load updated user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "John Doe")
        val savedBio = sharedPreferences.getString("bio", "System Admin")

        // Display updated name and bio
        profileName.text = savedName
        profileBio.text = savedBio

        // Edit button click listener
        editButton.setOnClickListener {
            // Navigate to Edit Profile page
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        // Log Out button click listener
        logoutButton.setOnClickListener {
            // Log out from Firebase Authentication
            firebaseAuth.signOut()

            // Redirect user to Login page
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close the current UserProfileActivity
        }
    }
}

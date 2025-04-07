package com.example.daisy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        supportActionBar?.title = "User Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Reference to the views
        val profileName: TextView = findViewById(R.id.profile_name)
        val email: TextView = findViewById(R.id.email)
        val phoneNumber: TextView = findViewById(R.id.phone_num)
        val editButton: Button = findViewById(R.id.edit_button)

        // Load user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "Full Name")
        val savedEmail = sharedPreferences.getString("email", "Email Address")
        val savedPhone = sharedPreferences.getString("phone", "Phone Number")

        // Set the user data in the TextViews
        profileName.text = savedName
        email.text = savedEmail
        phoneNumber.text = savedPhone

        // Edit Button click listener
        editButton.setOnClickListener {
            // Navigate to EditProfileActivity
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

}
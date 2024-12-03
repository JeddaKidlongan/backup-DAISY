package com.example.daisy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val nameEditText: EditText = findViewById(R.id.edit_name)
        val bioEditText: EditText = findViewById(R.id.edit_bio)
        val saveButton: Button = findViewById(R.id.save_button)

        // Load saved data into the fields
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "John Doe")
        val savedBio = sharedPreferences.getString("bio", "System Admin")

        nameEditText.setText(savedName)
        bioEditText.setText(savedBio)

        saveButton.setOnClickListener {
            // Save the edited name and bio
            val updatedName = nameEditText.text.toString()
            val updatedBio = bioEditText.text.toString()

            // Save to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("name", updatedName)
            editor.putString("bio", updatedBio)
            editor.apply()

            // Show confirmation and navigate back
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()

            // Go back to User Profile page
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
            finish() // Close the EditProfileActivity to avoid going back to it when pressing back button
        }
    }
}

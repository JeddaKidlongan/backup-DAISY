package com.example.daisy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage

    private lateinit var profileImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var saveButton: Button

    private val PICK_IMAGE_REQUEST = 1
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()

        // Bind UI components
        // Bind UI components
        profileImageView = findViewById(R.id.imageView)
        nameEditText = findViewById(R.id.edit_name)
        emailEditText = findViewById(R.id.edit_email)
        phoneEditText = findViewById(R.id.edit_phone_num)
        saveButton = findViewById(R.id.save_button)

        // Load user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPhone = sharedPreferences.getString("phone", "")

        nameEditText.setText(savedName)
        emailEditText.setText(savedEmail)
        phoneEditText.setText(savedPhone)

        // Set profile image click listener for uploading
        profileImageView.setOnClickListener {
            openImagePicker()
        }

        // Save button click listener
        saveButton.setOnClickListener {
            saveUserProfile()
        }
    }

    // Open image picker
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Handle image selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            profileImageUri = data.data
            profileImageView.setImageURI(profileImageUri)
        }
    }

    // Save user profile data and image
    private fun saveUserProfile() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", name)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.apply()

        if (profileImageUri != null) {
            uploadProfileImage { imageUrl ->
                editor.putString("profileImage", imageUrl)
                editor.apply()
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

                // Go back to UserProfileActivity
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()

            // Go back to UserProfileActivity
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Upload profile image to Firebase Storage
    private fun uploadProfileImage(onSuccess: (String) -> Unit) {
        val storageReference = storage.reference.child("profile_images/${UUID.randomUUID()}.jpg")

        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, profileImageUri)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = storageReference.putBytes(data)
        uploadTask.addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri.toString())
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
        }
    }

}
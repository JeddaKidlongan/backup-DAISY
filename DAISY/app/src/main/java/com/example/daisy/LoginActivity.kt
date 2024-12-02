package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daisy.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInEt.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }else {
                Toast.makeText(this, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show()
            }

        }

        }
    override fun onStart() {
        super.onStart()

        // Get the current user
        val currentUser = firebaseAuth.currentUser

        // Check if the user is signed in
        if (currentUser != null) {
            // Reload the user state to ensure it's not a deleted account
            currentUser.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (firebaseAuth.currentUser != null) {
                        // If the user still exists, go to DashBoardActivity
                        Log.d("LoginActivity", "User exists: ${currentUser.email}")
                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        // If the user has been deleted, stay on the login screen
                        Toast.makeText(
                            this,
                            "Account no longer exists, please sign in again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this, "Error reloading user data", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Log.d("LoginActivity", "No user logged in")
        }
    }
}
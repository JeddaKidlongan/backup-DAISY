package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Ensure NavHostFragment is correctly referenced and the NavController is initialized
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val realTimeCard: CardView = findViewById(R.id.camera_fragment)
        val learnSignLanguageCard: CardView = findViewById(R.id.learn_signlanguage_card)
        val sampleQuizCard: CardView = findViewById(R.id.sample_quiz_card)

        realTimeCard.setOnClickListener {
            // Use the navController to navigate to CameraFragment
            navController.navigate(R.id.camera_fragment)
        }

        // Implement the other card click listeners as needed
        learnSignLanguageCard.setOnClickListener {
            Toast.makeText(this, "COMING SOON!", Toast.LENGTH_SHORT).show()
        }

        sampleQuizCard.setOnClickListener {
            Toast.makeText(this, "COMING SOON!", Toast.LENGTH_SHORT).show()
        }

        // Initialize BottomNavigationView for Profile Navigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_user -> {
                    // Launch UserProfileActivity
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings Coming Soon!", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_messages -> {
                    Toast.makeText(this, "Forum Coming Soon!", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}

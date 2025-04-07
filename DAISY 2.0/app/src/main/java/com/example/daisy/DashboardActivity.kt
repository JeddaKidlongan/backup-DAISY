package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        // Ensure NavHostFragment is correctly referenced
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.camera_fragment -> bottomNavigation.visibility = View.GONE // Hide for Sign Challenge
                else -> bottomNavigation.visibility = View.VISIBLE // Show for everything else
            }
        }


        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> {
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                R.id.nav_logout -> {
                    showLogoutConfirmationDialog()
                    true
                }
                else -> false
            }
        }



        // Initialize CardViews and Button
        val realTimeCard: CardView = findViewById(R.id.camera_fragment)
        val learnSignLanguageCard: CardView = findViewById(R.id.learn_signlanguage_card)
        val startQuizCard: CardView = findViewById(R.id.start_Quiz_card)


        // Set Click Listeners for Cards
        realTimeCard.setOnClickListener {
            // Navigate to CameraFragment via NavController
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.camera_fragment)
        }

        learnSignLanguageCard.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        startQuizCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showLogoutConfirmationDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_logout_confirmation, null)

        val dialog = android.app.AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val yesButton = dialogView.findViewById<Button>(R.id.yesBtn)
        val noButton = dialogView.findViewById<Button>(R.id.noBtn)

        yesButton.setOnClickListener {
            dialog.dismiss()
            logoutUser()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun logoutUser() {
        firebaseAuth.signOut()

        val intent = Intent(this, SignUpActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}

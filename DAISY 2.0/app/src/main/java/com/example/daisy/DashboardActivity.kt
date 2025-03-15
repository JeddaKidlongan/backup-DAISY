package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.example.daisy.R.id.drawer_layout
import com.example.daisy.R.id.nav_view
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(drawer_layout)
        navView = findViewById(nav_view)

        // Setup Toolbar and Drawer Toggle
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.dashboard_toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle Navigation Drawer item clicks
        navView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemClick(menuItem)
            true
        }

        // Ensure NavHostFragment is correctly referenced
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Initialize CardViews
        val realTimeCard: CardView = findViewById(R.id.camera_fragment)
        val learnSignLanguageCard: CardView = findViewById(R.id.learn_signlanguage_card)
        val sampleQuizCard: CardView = findViewById(R.id.sample_quiz_card)

        // Set Click Listeners for Cards
        realTimeCard.setOnClickListener {
            navigateToFragment(R.id.camera_fragment)
        }

        learnSignLanguageCard.setOnClickListener {
            val intent = Intent(this, LearnSignLanguageActivity::class.java)
            startActivity(intent)
        }

        sampleQuizCard.setOnClickListener {
            val intent = Intent(this, SampleQuizActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle Navigation Drawer Clicks
    private fun handleNavigationItemClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> showSnackbar("Home Selected")
            R.id.nav_profile -> {
                startActivity(Intent(this, EditProfileActivity::class.java))
            }
            R.id.nav_logout -> logoutUser()
        }
    }

    // Logout User
    private fun logoutUser() {
        firebaseAuth.signOut()
        showSnackbar("Logged out successfully!")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    // Navigate to Fragments
    private fun navigateToFragment(fragmentId: Int) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(fragmentId)
        closeDrawer()
    }

    // Show Snackbar Message
    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
        closeDrawer()
    }

    // Close Drawer Function
    private fun closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    // Handle Back Press to Close Drawer First
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

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

        // Initialize CardViews and Button
        val realTimeCard: CardView = findViewById(R.id.camera_fragment)
        val learnSignLanguageCard: CardView = findViewById(R.id.learn_signlanguage_card)
        val startQuizCard: CardView = findViewById(R.id.start_Quiz_card)

        learnSignLanguageCard.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        startQuizCard.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle Navigation Drawer clicks
    private fun handleNavigationItemClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> showSnackbar("Home Selected")
            R.id.nav_profile -> {
                startActivity(Intent(this, EditProfileActivity::class.java))
            }
            R.id.nav_logout -> logoutUser()
        }
        closeDrawer()
    }

    // Logout User
    private fun logoutUser() {
        firebaseAuth.signOut()
        showSnackbar("Logged out successfully!")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    // Show Snackbar Message
    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    // Close Drawer Function
    private fun closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    // Handle Back Press to close drawer first
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

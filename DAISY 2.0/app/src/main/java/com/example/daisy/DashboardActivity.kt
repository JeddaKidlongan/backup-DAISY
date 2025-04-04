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
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
        // Inside onCreate
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener { item ->
            onNavigationItemSelected(item)
        }
        if (savedInstanceState == null) {
            // Don’t launch DashboardActivity from itself!
            navView.setCheckedItem(R.id.nav_home)
        }


        // Ensure NavHostFragment is correctly referenced
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

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


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
            R.id.nav_profile -> {
                startActivity(Intent(this, UserProfileActivity::class.java))
            }
            R.id.nav_edit_profile -> {
                startActivity(Intent(this, EditProfileActivity::class.java))
            }
            R.id.logout -> {
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
                // Add logout logic if needed
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

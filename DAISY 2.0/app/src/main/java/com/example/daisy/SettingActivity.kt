package com.example.daisy

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class SettingActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var btnChangePassword: Button
    private lateinit var btnDeleteAccount: Button
    private lateinit var btnLogout: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // Initialize views
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnChangePassword = findViewById(R.id.btn_change_password)
        btnDeleteAccount = findViewById(R.id.delete_account)
        btnLogout = findViewById(R.id.logout)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)

    }

    private fun setListeners() {

        // Change password
        btnChangePassword.setOnClickListener {
            Toast.makeText(this, "Change Password Clicked", Toast.LENGTH_SHORT).show()
        }

        // Delete account
        btnDeleteAccount.setOnClickListener {
            showDeleteAccountConfirmation()
        }

        // Logout
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun showDeleteAccountConfirmation() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Account")
        builder.setMessage("Are you sure you want to delete your account?")
        builder.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun logout() {
        try {
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error logging out: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}

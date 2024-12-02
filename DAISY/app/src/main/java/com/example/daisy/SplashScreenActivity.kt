package com.example.daisy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.daisy.databinding.ActivitySplashScreenBinding

//Splash screen activity is for keme keme before the login page / signup page of DAISY

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access the ImageView through binding
        binding.ssLogo.alpha = 0f
        binding.ssLogo.animate().setDuration(1000).alpha(1f).withEndAction {
            // Navigate to LoginActivity after the animation
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}
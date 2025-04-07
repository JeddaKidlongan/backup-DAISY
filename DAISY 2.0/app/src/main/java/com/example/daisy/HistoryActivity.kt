package com.example.daisy

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("MyScores", MODE_PRIVATE)
        val numbersScore = prefs.getInt("numbers_score", 0)
        val lettersScore = prefs.getInt("letters_score", 0)
        val wordsScore = prefs.getInt("words_score", 0)

        findViewById<TextView>(R.id.tvScoreNumbers).text = "Numbers Score: $numbersScore"
        findViewById<TextView>(R.id.tvScoreLetters).text = "Letters Score: $lettersScore"
        findViewById<TextView>(R.id.tvScoreWords).text = "Words Score: $wordsScore"

    }

}
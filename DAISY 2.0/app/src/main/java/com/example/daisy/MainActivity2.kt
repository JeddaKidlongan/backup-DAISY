package com.example.daisy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.daisy.databinding.ActivityMain2Binding
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Numbers CardView
        binding.numeroCard.setOnClickListener {
            startActivity(Intent(this, NumbersActivity::class.java))
        }

        // Letters CardView — now directly opens LettersActivity
        binding.alpabetoCard.setOnClickListener {
            startActivity(Intent(this, LettersActivity::class.java))
        }

        // Words CardView — now directly opens WordsActivity
        binding.salitaCard.setOnClickListener {
            startActivity(Intent(this, WordsActivity::class.java))
        }

        // Bulk video download
        val bulkDownloadWorkRequest = OneTimeWorkRequestBuilder<BulkDownloadWorker>().build()
        WorkManager.getInstance(this).enqueue(bulkDownloadWorkRequest)

        updateProgress()
    }

    override fun onResume() {
        super.onResume()
        updateProgress()
    }

    private fun updateProgress() {
        val prefs = getSharedPreferences("progress", MODE_PRIVATE)

        // Letters (A-Z)
        val lettersCompleted = prefs.getStringSet("letters_completed", emptySet())?.size ?: 0
        updateModuleProgress(binding.lettersProgress, binding.tvLettersPercent, lettersCompleted, 26)

        // Numbers (0–9)
        val numbersCompleted = prefs.getStringSet("numbers_completed", emptySet())?.size ?: 0
        updateModuleProgress(binding.numbersProgress, binding.tvNumbersPercent, numbersCompleted, 10)

        // Words (11 items)
        val wordsCompleted = prefs.getStringSet("words_completed", emptySet())?.size ?: 0
        updateModuleProgress(binding.wordsProgress, binding.tvWordsPercent, wordsCompleted, 11)
    }

    private fun updateModuleProgress(
        progressBar: CircularProgressIndicator,
        textView: TextView,
        completed: Int,
        total: Int
    ) {
        val percent = (completed.toFloat() / total * 100).toInt().coerceAtMost(100)
        progressBar.progress = percent
        textView.text = "$percent%"

        // Change color when completed
        if (percent == 100) {
            progressBar.setIndicatorColor(ContextCompat.getColor(this, R.color.green_500))
            textView.setTextColor(ContextCompat.getColor(this, R.color.green_500))
        }
    }
}

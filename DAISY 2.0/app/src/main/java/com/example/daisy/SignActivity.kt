package com.example.daisy

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.daisy.databinding.ActivitySignBinding
import java.io.File
import kotlin.math.abs

class SignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignBinding
    private var frontViewVideoUrl: String = ""
    private var sideViewVideoUrl: String = ""
    private lateinit var currentCategory: String
    private var currentList: List<String> = emptyList()
    private var currentWordList: List<Pair<String, String>> = emptyList()
    private var currentIndex: Int = 0
    private var currentVideoCode: String = ""
    private lateinit var gestureDetector: GestureDetector
    private var player: ExoPlayer? = null

    @androidx.annotation.OptIn(UnstableApi::class)
    @OptIn(UnstableApi::class)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Improved back button implementation
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val displayText = intent.getStringExtra("DISPLAY_TEXT") ?: ""
        val videoCode = intent.getStringExtra("VIDEO_CODE") ?: ""
        currentCategory = intent.getStringExtra("CATEGORY") ?: "letters"

        binding.txtSignTitle.text = when (currentCategory) {
            "letters" -> getString(R.string.sign_title_alphabet)
            "numbers" -> getString(R.string.sign_title_numbers)
            "words" -> getString(R.string.sign_title_words)
            else -> getString(R.string.sign_title_alphabet)
        }

        if (currentCategory == "words") {
            currentWordList = listOf(
                Pair("Magandang Umaga", "w_goodmorning"),
                Pair("Magandang Hapon", "w_goodafternoon"),
                Pair("Magandang Gabi", "w_goodevening"),
                Pair("Ingat ka", "w_takecare"),
                Pair("Paalam", "w_bye"),
                Pair("Tulong", "w_help"),
                Pair("Doctor", "w_doctor"),
                Pair("Hospital", "w_hospital"),
                Pair("Police", "w_police"),
                Pair("Masakit", "w_painful"),
                Pair("Emergency", "w_emergency")
            )
            currentIndex = currentWordList.indexOfFirst { it.second == videoCode }.takeIf { it != -1 } ?: 0
            val wordPair = currentWordList[currentIndex]
            binding.txtSign.text = wordPair.first
            currentVideoCode = wordPair.second
        } else {
            currentList = when (currentCategory) {
                "letters" -> ('a'..'z').map { "l$it" }
                "numbers" -> (0..9).map { "n$it" }
                else -> emptyList()
            }
            currentIndex = currentList.indexOf(videoCode).takeIf { it != -1 } ?: 0
            updateDisplay()
        }

        updateVideoUrls(currentVideoCode)
        setupExoPlayer()
        playVideo(frontViewVideoUrl, "front")

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float
            ): Boolean {
                val diffX = e2.x - (e1?.x ?: 0f)
                if (abs(diffX) > 100 && abs(velocityX) > 100) {
                    when {
                        diffX < 0 -> goToNextItem()
                        diffX > 0 -> goToPreviousItem()
                    }
                    return true
                }
                return false
            }
        })

        binding.root.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun goToNextItem() {
        if (currentCategory == "words") {
            if (currentIndex < currentWordList.size - 1) {
                currentIndex++
                updateDisplay()
            } else if (currentIndex == currentWordList.size - 1) {
                showQuizPromptDialog("Words Quiz", WordsQuizActivity::class.java)
            }
        } else {
            if (currentIndex < currentList.size - 1) {
                currentIndex++
                updateDisplay()
            } else if (currentIndex == currentList.size - 1) {
                when (currentCategory) {
                    "letters" -> showQuizPromptDialog("Letters Quiz", LettersQuizActivity::class.java)
                    "numbers" -> showQuizPromptDialog("Numbers Quiz", NumbersQuizActivity::class.java)
                }
            }
        }
    }

    private fun goToPreviousItem() {
        if (currentIndex > 0) {
            currentIndex--
            updateDisplay()
        } else {
            Toast.makeText(this, "First item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDisplay() {
        if (currentCategory == "words") {
            val wordPair = currentWordList[currentIndex]
            binding.txtSign.text = wordPair.first
            currentVideoCode = wordPair.second
            updateVideoUrls(currentVideoCode)
            playVideo(frontViewVideoUrl, "front")
            trackProgress(wordPair.first)
        } else {
            val newVideoCode = currentList[currentIndex]
            val rawValue = newVideoCode.substring(1)
            val finalText = if (currentCategory == "numbers") {
                val numberWords = mapOf(
                    "0" to "Zero", "1" to "One", "2" to "Two", "3" to "Three",
                    "4" to "Four", "5" to "Five", "6" to "Six", "7" to "Seven",
                    "8" to "Eight", "9" to "Nine"
                )
                val written = numberWords[rawValue] ?: rawValue
                "$written\n$rawValue"
            } else {
                rawValue.uppercase()
            }
            binding.txtSign.text = finalText
            currentVideoCode = newVideoCode
            updateVideoUrls(newVideoCode)
            playVideo(frontViewVideoUrl, "front")
            trackProgress(rawValue)
        }
        updateSwipeIndicators()
    }

    private fun updateSwipeIndicators() {
        val isFirst = currentIndex == 0
        val isLast = when (currentCategory) {
            "words" -> currentIndex == currentWordList.size - 1
            else -> currentIndex == currentList.size - 1
        }

        binding.leftSwipeIndicator.visibility = if (isFirst) View.GONE else View.VISIBLE
        binding.rightSwipeIndicator.visibility = if (isLast) View.GONE else View.VISIBLE

        if (binding.leftSwipeIndicator.visibility == View.VISIBLE) {
            binding.leftSwipeIndicator.alpha = 1f
            binding.leftSwipeIndicator.postDelayed({
                binding.leftSwipeIndicator.animate().alpha(0f).setDuration(500)
                    .withEndAction { binding.leftSwipeIndicator.visibility = View.INVISIBLE }
            }, 2000)
        }

        if (binding.rightSwipeIndicator.visibility == View.VISIBLE) {
            binding.rightSwipeIndicator.alpha = 1f
            binding.rightSwipeIndicator.postDelayed({
                binding.rightSwipeIndicator.animate().alpha(0f).setDuration(500)
                    .withEndAction { binding.rightSwipeIndicator.visibility = View.INVISIBLE }
            }, 2000)
        }
    }

    private fun trackProgress(displayText: String) {
        val key = "${currentCategory}_completed"
        val prefs = getSharedPreferences("progress", MODE_PRIVATE)
        val completed = prefs.getStringSet(key, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        if (completed.add(displayText)) {
            prefs.edit().putStringSet(key, completed).apply()
        }
    }

    private fun showQuizPromptDialog(title: String, targetActivity: Class<*>) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("You've reached the end! Would you like to take the quiz now?")
            .setPositiveButton("Yes") { _, _ ->
                startActivity(Intent(this, targetActivity))
            }
            .setNegativeButton("Not now", null)
            .show()
    }

    @androidx.annotation.OptIn(UnstableApi::class)
    @OptIn(UnstableApi::class)
    private fun updateVideoUrls(videoCode: String) {
        frontViewVideoUrl = VideoRepository.getCloudinaryUrl(videoCode, "front")
        sideViewVideoUrl = VideoRepository.getCloudinaryUrl(videoCode, "side")
    }

    @UnstableApi
    @OptIn(UnstableApi::class)
    private fun setupExoPlayer() {
        player = ExoPlayer.Builder(this).build()
        binding.videoPlayer.player = player
        binding.videoPlayer.useController = false
        binding.videoPlayer.visibility = View.VISIBLE
        binding.videoPlayer.setKeepContentOnPlayerReset(true)
        player?.repeatMode = Player.REPEAT_MODE_ALL

        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> Log.d("VideoDebug", "Video is ready to play")
                    Player.STATE_BUFFERING -> Log.d("VideoDebug", "Buffering...")
                    Player.STATE_ENDED -> Log.d("VideoDebug", "Video playback ended")
                    Player.STATE_IDLE -> Log.d("VideoDebug", "Player is idle")
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                Log.e("VideoDebug", "Playback error: ${error.message}")
                Toast.makeText(this@SignActivity, "Video error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun playVideo(onlineUrl: String, viewType: String) {
        val uri = getLocalVideoUri(currentVideoCode, viewType, onlineUrl)
        player?.stop()
        player?.clearMediaItems()
        player?.setMediaItem(MediaItem.fromUri(uri))
        player?.prepare()
        player?.playWhenReady = true
        player?.setPlaybackSpeed(1.2f)
        player?.volume = 0f
    }

    private fun getLocalVideoUri(videoCode: String, viewType: String, onlineUrl: String): Uri {
        val videosDir = File(getExternalFilesDir(null), "videos")
        val fileName = "${videoCode}_${viewType}.mp4"
        val localFile = File(videosDir, fileName)
        return if (localFile.exists()) Uri.fromFile(localFile) else onlineUrl.toUri()
    }

    override fun onStart() {
        super.onStart()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}

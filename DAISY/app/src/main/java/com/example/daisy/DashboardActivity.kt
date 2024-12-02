package com.example.daisy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daisy.databinding.DashboardBinding
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: DashboardBinding
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer
    private var isVoiceCommandActive = false
    private val REQUEST_AUDIO_PERMISSION_CODE = 1
    private val AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestAudioPermission()

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported")
                } else {
                    speak("Welcome to DAISY, how may I assist you?")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }

        setupButtonListeners()

        binding.micBtn.setOnClickListener {
            // Toggle voice command state
            isVoiceCommandActive = !isVoiceCommandActive
            if (isVoiceCommandActive) {
                speak("Voice Command Activated, say your command.")
                startListeningForVoiceCommands() // Start listening
            } else {
                speak("Voice Command Deactivated")
                stopListeningForVoiceCommands() // Stop listening
            }
        }
    }

    private fun setupButtonListeners() {
        binding.assistiveCommunicationBtn.setOnClickListener {
            Toast.makeText(this, "Assistive Communication Selected", Toast.LENGTH_SHORT).show()
            speak("Assistive Communication")
        }

        binding.moneyDetectionBtn.setOnClickListener {
            Toast.makeText(this, "Money Detection Selected", Toast.LENGTH_SHORT).show()
            speak("Money Detection")
        }

        binding.navigationAssistantBtn.setOnClickListener {
            Toast.makeText(this, "Navigation Assistant Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NavigationAssistActivity::class.java)
            startActivity(intent)
            speak("Navigation Assistant")
        }

        binding.transportationBtn.setOnClickListener {
            Toast.makeText(this, "Transportation Selected", Toast.LENGTH_SHORT).show()
            speak("Transportation Assistant")
        }

        binding.emergencyBtn.setOnClickListener {
            Toast.makeText(this, "Emergency", Toast.LENGTH_SHORT).show()
            speak("EMERGENCY DIAL, press the screen to dial 911")
        }

        binding.homeBtn.setOnClickListener {
            // Implement home functionality here
        }

        binding.settingsBtn.setOnClickListener {
            // Implement settings functionality here
        }
    }

    private fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle) {
                Log.d("VoiceCommand", "Ready for speech")
            }

            override fun onBeginningOfSpeech() {
                Log.d("VoiceCommand", "Speech has begun")
            }

            override fun onEndOfSpeech() {
                Log.d("VoiceCommand", "Speech has ended")
                if (isVoiceCommandActive) {
                    startListeningForVoiceCommands() // Restart listening after speech ends
                }
            }

            override fun onError(error: Int) {
                Log.e("VoiceCommand", "Error: $error")
                // Restart listening on error
                if (isVoiceCommandActive) {
                    startListeningForVoiceCommands()
                }
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.let {
                    for (result in it) {
                        Log.d("VoiceCommand", "Recognized: $result")
                        handleVoiceCommand(result)
                    }
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
        })
    }

    private fun startListeningForVoiceCommands() {
        speechRecognizer.startListening(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        })
    }

    private fun stopListeningForVoiceCommands() {
        speechRecognizer.stopListening()
    }

    private fun handleVoiceCommand(command: String) {
        // Check if voice commands are active
        if (!isVoiceCommandActive) {
            return // Exit the function if voice commands are inactive
        }

        when {
            command.contains("DAISY", ignoreCase = true) -> {
                // Check for specific commands after the keyword
                when {
                    command.contains("voice command off", ignoreCase = true) -> {
                        speak("Voice Command Deactivated.")
                        stopListeningForVoiceCommands() // Stop listening for commands
                        isVoiceCommandActive = false // Update state variable
                    }
                    command.contains("open navigate", ignoreCase = true) -> {
                        speak("Opening navigation.")
                        // Start navigation feature
                        val intent = Intent(this, NavigationAssistActivity::class.java)
                        startActivity(intent)
                        speak("Where do you want to go?")
                    }
                    command.contains("open assistive communication", ignoreCase = true) -> {
                        speak("Sorry, this feature is still in development.")
                        // Start assistive communication feature
                    }
                    command.contains("open money detection", ignoreCase = true) -> {
                        speak("Sorry, this feature is still in development.")
                        // Start money detection feature
                    }
                    command.contains("open transportation assistant", ignoreCase = true) -> {
                        speak("Sorry, this feature is still in development.")
                        // Start transportation assistant feature
                    }
                    command.contains("back to menu", ignoreCase = true) -> {
                        speak("Returning to menu.")
                        stopListeningForVoiceCommands() // Stop listening for commands
                        isVoiceCommandActive = false // Update state variable
                        // Start DashboardActivity
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    }
                    command.contains("emergency dial", ignoreCase = true) -> {
                        speak("Sorry, this feature is still in development.")
                        // Implement emergency dialing logic here
                    }
                    // Add more commands as needed
                    else -> {
                        speak("Command not recognized. Please try again.")
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
        speechRecognizer.destroy() // Clean up the recognizer
    }

    private fun requestAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, AUDIO_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(AUDIO_PERMISSION),
                REQUEST_AUDIO_PERMISSION_CODE)
        } else {
            initializeSpeechRecognizer()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_AUDIO_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initializeSpeechRecognizer() // Permission granted
                } else {
                    Toast.makeText(this, "Audio permission is required for voice commands", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

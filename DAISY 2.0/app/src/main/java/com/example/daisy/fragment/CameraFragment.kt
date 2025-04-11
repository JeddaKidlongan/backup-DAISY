package com.example.daisy.fragment

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daisy.GestureRecognizerHelper
import com.example.daisy.MainViewModel
import com.example.daisy.databinding.FragmentCameraBinding
import com.google.mediapipe.tasks.vision.core.RunningMode
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import com.example.daisy.R
import androidx.navigation.findNavController
import androidx.appcompat.app.AlertDialog

@Suppress("DEPRECATION")
class CameraFragment : Fragment(), GestureRecognizerHelper.GestureRecognizerListener {

    companion object {
        private const val TAG = "Sign Translator"
    }

    private var _fragmentCameraBinding: FragmentCameraBinding? = null
    private val fragmentCameraBinding get() = _fragmentCameraBinding!!

    private lateinit var gestureRecognizerHelper: GestureRecognizerHelper
    private val viewModel: MainViewModel by activityViewModels()
    private var defaultNumResults = 1
    private val gestureRecognizerResultAdapter: GestureRecognizerResultsAdapter by lazy {
        GestureRecognizerResultsAdapter().apply { updateAdapterSize(defaultNumResults) }
    }
    private var preview: Preview? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var camera: Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var cameraFacing = CameraSelector.LENS_FACING_FRONT
    private lateinit var backgroundExecutor: ExecutorService
    private lateinit var challengeLetter: String

    // Challenge Game Variables
    private var score = 0
    private var roundCount = 0
    private var timer: CountDownTimer? = null
    private val challengeDuration: Long = 5000
    private var isChallengeInProgress = false
    private var lastGestureTime: Long = 1
    private val debounceTime = 1000

    // Flag to mark round completion
    private var roundCompleted = false

    override fun onResume() {
        super.onResume()
        if (!PermissionsFragment.hasPermissions(requireContext())) {
            requireActivity().findNavController(R.id.fragment_container)
                .navigate(R.id.action_camera_to_permissions)
        }
        backgroundExecutor.execute {
            if (gestureRecognizerHelper.isClosed()) {
                gestureRecognizerHelper.setupGestureRecognizer()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (this::gestureRecognizerHelper.isInitialized) {
            viewModel.setMinHandDetectionConfidence(gestureRecognizerHelper.minHandDetectionConfidence)
            viewModel.setMinHandTrackingConfidence(gestureRecognizerHelper.minHandTrackingConfidence)
            viewModel.setMinHandPresenceConfidence(gestureRecognizerHelper.minHandPresenceConfidence)
            viewModel.setDelegate(gestureRecognizerHelper.currentDelegate)
            backgroundExecutor.execute { gestureRecognizerHelper.clearGestureRecognizer() }
        }
    }

    override fun onDestroyView() {
        _fragmentCameraBinding = null
        super.onDestroyView()
        backgroundExecutor.shutdown()
        backgroundExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _fragmentCameraBinding = FragmentCameraBinding.inflate(inflater, container, false)
        return fragmentCameraBinding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(fragmentCameraBinding.recyclerviewResults) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gestureRecognizerResultAdapter
        }
        val tvChallengeLetter = fragmentCameraBinding.tvChallengeLetter
        val tvChallengeResult = fragmentCameraBinding.tvChallengeResult
        val tvScore = fragmentCameraBinding.tvScore
        val btnStartChallenge = fragmentCameraBinding.btnStartChallenge

        btnStartChallenge.setOnClickListener {
            // Show instructions in an AlertDialog before starting challenge
            AlertDialog.Builder(requireContext())
                .setTitle("Sign Challenge Instructions")
                .setMessage("In this challenge, a letter from A to I will be displayed. Your task is to sign the corresponding letter. Tap OK to begin the challenge.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ ->
                    // Reset challenge variables on start
                    score = 0
                    roundCount = 0
                    startChallenge(tvChallengeLetter, tvChallengeResult, tvScore)
                    dialog.dismiss()
                }
                .show()
        }

        fragmentCameraBinding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        fragmentCameraBinding.btnSwitchCamera.setOnClickListener {
            cameraFacing = if (cameraFacing == CameraSelector.LENS_FACING_FRONT)
                CameraSelector.LENS_FACING_BACK else CameraSelector.LENS_FACING_FRONT
            setUpCamera()
        }

        backgroundExecutor = Executors.newSingleThreadExecutor()
        fragmentCameraBinding.viewFinder.post { setUpCamera() }

        backgroundExecutor.execute {
            gestureRecognizerHelper = GestureRecognizerHelper(
                context = requireContext(),
                runningMode = RunningMode.LIVE_STREAM,
                minHandDetectionConfidence = viewModel.currentMinHandDetectionConfidence,
                minHandTrackingConfidence = viewModel.currentMinHandTrackingConfidence,
                minHandPresenceConfidence = viewModel.currentMinHandPresenceConfidence,
                currentDelegate = viewModel.currentDelegate,
                gestureRecognizerListener = this
            )
        }
    }

    private fun startChallenge(
        tvChallengeLetter: TextView, tvChallengeResult: TextView, tvScore: TextView
    ) {
        fragmentCameraBinding.btnStartChallenge.visibility = View.GONE
        tvScore.visibility = View.VISIBLE
        tvScore.text = "Score: $score"
        generateNewChallenge(tvChallengeLetter, tvChallengeResult)
        startChallengeTimer(tvChallengeResult)
    }

    private fun processDetectedSign(detectedSign: String, tvChallengeResult: TextView) {
        if (::challengeLetter.isInitialized && !roundCompleted) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastGestureTime >= debounceTime) {
                val cleanedDetectedSign = detectedSign.trim().uppercase(Locale.ROOT)
                val cleanedChallengeLetter = challengeLetter.trim().uppercase(Locale.ROOT)
                lastGestureTime = currentTime

                if (cleanedDetectedSign == cleanedChallengeLetter) {
                    roundCompleted = true
                    score++
                    tvChallengeResult.text = "✅ Correct!"
                    fragmentCameraBinding.tvScore.text = "Score: $score"
                } else {
                    tvChallengeResult.text = "❌ Incorrect! Detected: $cleanedDetectedSign"
                }
            }
        }
    }

    private fun proceedToNextChallenge(tvChallengeResult: TextView) {
        roundCount++
        if (roundCount >= 9) {
            AlertDialog.Builder(requireContext())
                .setTitle("Challenge Complete!")
                .setMessage("Final Score: $score out of $roundCount")
                .setCancelable(false)
                .setPositiveButton("Retry") { dialog, _ ->
                    score = 0
                    roundCount = 0
                    fragmentCameraBinding.tvScore.text = "Score: $score"
                    generateNewChallenge(fragmentCameraBinding.tvChallengeLetter, tvChallengeResult)
                    startChallengeTimer(tvChallengeResult)
                    dialog.dismiss()
                }
                .setNegativeButton("Exit") { dialog, _ ->
                    // Pop the fragment rather than finishing the entire app
                    requireActivity().onBackPressed()
                    dialog.dismiss()
                }
                .show()
            isChallengeInProgress = false
        } else {
            generateNewChallenge(fragmentCameraBinding.tvChallengeLetter, tvChallengeResult)
            startChallengeTimer(tvChallengeResult)
            fragmentCameraBinding.tvScore.text = "Score: $score"
            roundCompleted = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun generateNewChallenge(tvChallengeLetter: TextView, tvChallengeResult: TextView) {
        if (roundCount >= 9) {
            tvChallengeResult.text = "Challenge Complete! Final Score: $score"
            return
        }
        isChallengeInProgress = true
        challengeLetter = ('A'..'I').random().toString()
        tvChallengeResult.text = "Waiting for sign..."
        tvChallengeLetter.text = "Sign: $challengeLetter"
        roundCompleted = false
    }

    private fun startChallengeTimer(tvChallengeResult: TextView) {
        timer?.cancel()
        timer = object : CountDownTimer(challengeDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                tvChallengeResult.text = "Time Left: $secondsRemaining"
            }

            override fun onFinish() {
                if (roundCompleted) {
                    tvChallengeResult.text = "✅ Correct!"
                } else {
                    tvChallengeResult.text = "❌ Time's up! Next Letter"
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    proceedToNextChallenge(tvChallengeResult)
                }, 3000)
            }
        }
        timer?.start()
    }

    private fun setUpCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindCameraUseCases()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun bindCameraUseCases() {
        val cameraProvider = cameraProvider ?: throw IllegalStateException("Camera initialization failed.")
        val cameraSelector = CameraSelector.Builder().requireLensFacing(cameraFacing).build()

        preview = Preview.Builder().setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setTargetRotation(fragmentCameraBinding.viewFinder.display.rotation)
            .build()

        imageAnalyzer = ImageAnalysis.Builder().setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setTargetRotation(fragmentCameraBinding.viewFinder.display.rotation)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build().also {
                it.setAnalyzer(backgroundExecutor) { image ->
                    recognizeHand(image)
                }
            }

        cameraProvider.unbindAll()
        try {
            camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
            preview?.setSurfaceProvider(fragmentCameraBinding.viewFinder.surfaceProvider)
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }

    private fun recognizeHand(imageProxy: ImageProxy) {
        gestureRecognizerHelper.recognizeLiveStream(imageProxy = imageProxy)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        imageAnalyzer?.targetRotation = fragmentCameraBinding.viewFinder.display.rotation
    }

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {
        activity?.runOnUiThread {
            _fragmentCameraBinding?.let {
                val detectedSign = resultBundle.results
                    .flatMap { it.gestures().flatten() }
                    .firstOrNull()?.categoryName()

                if (detectedSign != null && detectedSign != "No gesture detected") {
                    processDetectedSign(detectedSign, fragmentCameraBinding.tvChallengeResult)
                    gestureRecognizerResultAdapter.updateResults(
                        resultBundle.results.firstOrNull()?.gestures()?.flatten() ?: emptyList()
                    )
                }
            }
        }
    }

    override fun onError(error: String, errorCode: Int) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            gestureRecognizerResultAdapter.updateResults(emptyList())
        }
    }
}

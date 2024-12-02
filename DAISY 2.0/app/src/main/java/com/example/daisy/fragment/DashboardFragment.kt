//package com.example.daisy.fragment
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.cardview.widget.CardView
//import com.example.daisy.R
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Find the CardViews by their IDs
//        val realTimeCard: CardView = view.findViewById(R.id.camera_fragment)
//        val learnSignLanguageCard: CardView = view.findViewById(R.id.learn_signlanguage_card)
//        val sampleQuizCard: CardView = view.findViewById(R.id.sample_quiz_card)
//        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.bottom_navigation)
//
//        // Set up click listeners for CardViews
//        realTimeCard.setOnClickListener {
//            // Navigate to the Real-Time Sign Language activity
//            Toast.makeText(requireContext(), "Real-Time Sign Language clicked!", Toast.LENGTH_SHORT).show()
//            // Example Intent:
//             val intent = Intent(requireContext(), CameraFragment::class.java)
//             startActivity(intent)
//        }
//
//        learnSignLanguageCard.setOnClickListener {
//            // Navigate to the Learn Sign Language activity
//            Toast.makeText(requireContext(), "Learn Sign Language clicked!", Toast.LENGTH_SHORT).show()
//            // Example Intent:
//            // val intent = Intent(requireContext(), LearnSignLanguageActivity::class.java)
//            // startActivity(intent)
//        }
//
//        sampleQuizCard.setOnClickListener {
//            // Navigate to the Sample Quiz activity
//            Toast.makeText(requireContext(), "Sample Quiz clicked!", Toast.LENGTH_SHORT).show()
//            // Example Intent:
//            // val intent = Intent(requireContext(), SampleQuizActivity::class.java)
//            // startActivity(intent)
//        }
//
//        // Set up the BottomNavigationView listener
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_settings -> {
//                    Toast.makeText(requireContext(), "Settings selected!", Toast.LENGTH_SHORT).show()
//                    // Example Intent:
//                    // val intent = Intent(requireContext(), SettingsActivity::class.java)
//                    // startActivity(intent)
//                    true
//                }
//                R.id.nav_messages -> {
//                    Toast.makeText(requireContext(), "Messages selected!", Toast.LENGTH_SHORT).show()
//                    // Example Intent:
//                    // val intent = Intent(requireContext(), MessagesActivity::class.java)
//                    // startActivity(intent)
//                    true
//                }
//                R.id.nav_user -> {
//                    Toast.makeText(requireContext(), "User selected!", Toast.LENGTH_SHORT).show()
//                    // Example Intent:
//                    // val intent = Intent(requireContext(), UserProfileActivity::class.java)
//                    // startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}

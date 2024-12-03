plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id ("de.undercouch.download") version "5.0.1"
}

android {
    namespace = "com.example.daisy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.daisy"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

// Define extra properties for asset directories
extra["ASSET_DIR"] = "app/src/main/assets"
extra["TEST_ASSETS_DIR"] = "app/src/androidTest/assets"

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.gridlayout)
    implementation(libs.google.maps)
    implementation(libs.places)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.camera.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Recycle View
    implementation (libs.androidx.recyclerview)

    // Navigation library
    implementation(libs.androidx.navigation.fragment.ktx.v253)
    implementation(libs.androidx.navigation.ui.ktx)

    // CameraX Libraries
    implementation(libs.androidx.camera.core.v120alpha02)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    // WindowManager
    implementation(libs.androidx.window)

    // Unit and Instrumented Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)

    // Mediapipe Library
    implementation(libs.tasks.vision)
    implementation ("com.google.firebase:firebase-auth:21.1.0") // Add Firebase Auth dependency
    implementation ("com.google.firebase:firebase-core:21.1.0")


}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin") // Dagger Hilt plugin
    id("kotlin-kapt") // Kapt for annotation processing
}

android {
    namespace = "com.example.dependencyinjectiondagger"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dependencyinjectiondagger"
        minSdk = 25
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

    buildFeatures {
        viewBinding = true // Enable View Binding
    }

    kapt {
        correctErrorTypes = true // Fix for kapt error types
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dagger Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.52")
    kapt ("com.google.dagger:hilt-android-compiler:2.52")

    // Use hilt-navigation-fragment instead of hilt-lifecycle-viewmodel
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // ViewModel and Fragment dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.fragment:fragment-ktx:1.8.4")
}
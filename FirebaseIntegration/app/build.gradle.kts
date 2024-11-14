plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.firebaseintegration"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firebaseintegration"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Firebase BoM (Bill of Materials)
    implementation (libs.firebase.bom)

    // Firebase Analytics
    implementation ("com.google.firebase:firebase-analytics-ktx:22.1.2")

    // Firebase Authentication
    //implementation (libs.firebase.auth)

    // Firebase Firestore (for database)
    implementation ("com.google.firebase:firebase-firestore-ktx:25.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4") // Ensure you are using a stable version
}
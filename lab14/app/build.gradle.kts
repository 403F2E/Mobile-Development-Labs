plugins {
    alias(libs.plugins.android.application)
}
android {
    namespace = "com.example.securestoragelab14"
    compileSdk {
        version = release(36) { minorApiLevel = 1 }
    }
    defaultConfig {
        // 403f2e has configured the application ID
        applicationId = "com.example.securestoragelab14"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        // 403f2e has set Java compatibility to version 11
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    // 403f2e has added necessary dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
}

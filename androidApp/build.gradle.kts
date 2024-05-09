plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "uz.otamurod.kmp.movieapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "uz.otamurod.kmp.movieapp.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    // Koin Dependency Injection Library
    implementation(libs.koin.androidx.compose)
    // Coil Image Loading Library
    implementation(libs.coil.compose)
    // Navigation Component
    implementation(libs.androidx.navigation.compose)
    // Accompanist System UI Controller Library
    implementation(libs.accompanist.systemuicontroller)

    debugImplementation(libs.compose.ui.tooling)
}
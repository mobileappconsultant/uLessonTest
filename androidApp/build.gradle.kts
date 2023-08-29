plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
    kotlin("plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.arkangel.ulessontechnicaltest.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.arkangel.ulessontechnicaltest.android"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.media.session)
    implementation(libs.androidx.media3.datasource.okhtpp)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose.navigation)
    implementation(libs.koin.compose)
    implementation(libs.compose.navigation)
    implementation(libs.compose.destinations)
    ksp(libs.compose.destinations.ksp)
}

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
    kotlin("plugin.serialization") version "1.8.21"
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.arkangel.ulessontechnicaltest.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.arkangel.ulessontechnicaltest.android"
        minSdk = 23
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
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
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
    implementation(libs.lottie.compose)
    implementation(libs.compose.media)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.ulesson.shared)
    implementation(libs.kotlinx.serialization)
    implementation(libs.ktor.client)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.multiplatform.settings)
    implementation(libs.multiplatform.settings.noarg)
    implementation(libs.koin.core)
    ksp(libs.compose.destinations.ksp)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
}

koverAndroid {
    common {
        filters {
            includes {
                packages(
                    "com.arkangel.*"
                )
            }
            excludes {
                annotatedBy("*Composable*", "*Generated*", "*ExperimentalSerializationApi")
                classes(
                    "*_*Factory.*",
                    "*_Factory.*",
                    "Hilt_*",
                    "*_Hilt*",
                    "*__Factory",
                    "dagger.hilt.*",
                    "hilt_aggregated_deps.*",
                    "*_Factory",
                    "*_Factory\$*",
                    "*_*Factory",
                    "*_*Factory\$*",
                    "*_Impl",
                    "*_Impl\$*",
                    "*Composable*",
                    "*ui*",
                    "*serializer",
                    "*Activity",
                    "*Activity\$*",
                    "*.databinding.*",
                    "*.BuildConfig",
                    // excludes debug classes
                    "*.DebugUtil",

                    "com.arkangel.ulessontechnicaltest.ULessonTechnicalTestApplication*",
                    "com.arkangel.ulessontechnicaltest.android.ComposableSingletons*",
                    "com.arkangel.ulessontechnicaltest.android.MainActivity*",
                    "com.arkangel.ulessontechnicaltest.android.MyApplicationThemeKt*",
                    "com.arkangel.ulessontechnicaltest.android.TerminalStateNotificationHelper*",
                    "com.arkangel.ulessontechnicaltest.android.VideoDownloadService*",
                    "com.arkangel.ulessontechnicaltest.android.features.NavArgsGettersKt*",
                    "com.arkangel.ulessontechnicaltest.android.features.NavGraph*",
                    "com.arkangel.ulessontechnicaltest.android.features.NavGraphs*",
                    "com.arkangel.ulessontechnicaltest.android.features.SingleModuleExtensionsKt*",
                    "com.arkangel.ulessontechnicaltest.android.features.destinations.DirectionDestination*",
                    "com.arkangel.ulessontechnicaltest.android.features.destinations.HomeScreenDestination*",
                    "com.arkangel.ulessontechnicaltest.android.features.destinations.LessonPlayerScreenDestination*",
                    "com.arkangel.ulessontechnicaltest.android.features.destinations.SubjectInfoScreenDestination*",
                    "com.arkangel.ulessontechnicaltest.android.features.destinations.TypedDestination*",
                    "com.arkangel.ulessontechnicaltest.android.features.home.ComposableSingletons*",
                    "com.arkangel.ulessontechnicaltest.android.features.home.HomeScreenKt*",
                    "com.arkangel.ulessontechnicaltest.android.features.lesson_player.LessonPlayerScreenKt*",
                    "com.arkangel.ulessontechnicaltest.android.features.lesson_player.LessonPlayerScreenNavArgs*",
                    "com.arkangel.ulessontechnicaltest.android.utils.ContextUtilsKt*",
                    "com.arkangel.ulessontechnicaltest.android.utils.DownloadManagerImpl*",
                    "com.arkangel.ulessontechnicaltest.android.utils.PlayerUtil*",
                    "com.arkangel.ulessontechnicaltest.android.utils.DateUtilsWrapper*",
                    "com.arkangel.ulessontechnicaltest.android.features.navtype.LessonsWrapperNavType*",
                    "com.arkangel.ulessontechnicaltest.android.features.navtype.LessonsWrapperNavTypeKt*",
                    "com.arkangel.ulessontechnicaltest.android.features.navtype.SubjectNavType*",
                    "com.arkangel.ulessontechnicaltest.android.features.navtype.SubjectNavTypeKtz*",
                )
            }
        }
    }
}

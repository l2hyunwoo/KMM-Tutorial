plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.github.hyunwoo.todo.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.github.hyunwoo.todo.android"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    with(Dependencies.Koin) {
        implementation(core)
        implementation(test)
    }

    with(Dependencies.Kotlin) {
        implementation(datetime)
        implementation(coroutines)
    }

    with(Dependencies.Androidx) {
        implementation(core)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(swipeRefreshLayout)
        implementation(recyclerView)
        implementation(cardView)
    }
}
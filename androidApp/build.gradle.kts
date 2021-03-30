
plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0-rc01")

    val composeVersion = "1.0.0-beta03"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.compiler:compiler:$composeVersion")

    val constraintLayoutCompose = "1.0.0-alpha03"
    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintLayoutCompose")

    val composeActivities = "1.3.0-alpha03"
    implementation("androidx.activity:activity-compose:$composeActivities")

    val navigationCompose = "1.0.0-alpha08"
    implementation("androidx.navigation:navigation-compose:$navigationCompose")

    // Needed for viewmodel to do constructor injection
    val hiltNavigation = "1.0.0-alpha03"
    implementation("androidx.hilt:hilt-navigation:$hiltNavigation")

    val hilt = "2.31.2-alpha"
    kapt("com.google.dagger:hilt-compiler:$hilt")
    implementation("com.google.dagger:hilt-android:$hilt")

//    val hiltLifecycleViewModel = "1.0.0-alpha03"
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifecycleViewModel")

    val accompanistCoil = "0.7.0"
    implementation("com.google.accompanist:accompanist-coil:$accompanistCoil")

    val kotlinDatetime = "0.1.1"
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetime")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.example.kmmplayground.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta03"
    }
}



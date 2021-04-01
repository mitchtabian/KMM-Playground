
object Versions{
    const val compileSdkVersion = 29
    const val minSdkVersion = 24
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0"

    const val accompanistCoil = "0.7.0"
    const val androidMaterial = "1.3.0"
    const val appCompat = "1.3.0-rc01"
    const val composeVersion = "1.0.0-beta03"
    const val constraintLayoutCompose = "1.0.0-alpha03"
    const val composeActivities = "1.3.0-alpha03"
    const val gradleBuildTools = "7.0.0-alpha09"
    const val hiltNavigation = "1.0.0-alpha03"
    const val hilt = "2.31.2-alpha"
    const val junit4 = "4.13"
    const val kotlin = "1.4.31"
    const val kotlinxDatetime = "0.1.1"
    const val kotlinxSerialization = "1.1.0"
    const val kotlinxCoroutines = "1.3.2"
    const val ktor =  "1.5.2"
    const val navigationCompose = "1.0.0-alpha08"
    const val sqlDelight = "1.4.3"
}

object Accompanist {
    const val coil = "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val material = "androidx.compose.material:material:${Versions.composeVersion}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivities}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.androidMaterial}"
}

object Gradle {
    const val tools = "com.android.tools.build:gradle:${Versions.gradleBuildTools}"
}

object Hilt {
    const val navigation = "androidx.hilt:hilt-navigation:${Versions.hiltNavigation}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object Junit {
    const val core = "junit:junit:${Versions.junit4}"
}

object Kotlin {
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}

object Kotlinx {
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
    const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
}

object Ktor {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
}

object SQLDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
}










buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("com.android.tools.build:gradle:7.0.0-alpha09")

        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")

        val hilt = "2.31.2-alpha"
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
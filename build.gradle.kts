buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Kotlin.gradlePlugin)
        classpath(Kotlin.serialization)
        classpath(Gradle.tools)
        classpath(Hilt.androidGradlePlugin)
        classpath(SQLDelight.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
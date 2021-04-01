import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
    id("com.android.library")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}
kotlin {
    android()
    val sdkName: String? = System.getenv("SDK_NAME")
    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlinx.datetime)
                implementation(Kotlinx.serializationJson)
                implementation(Ktor.core)
                implementation(Ktor.json)
                implementation(Ktor.serialization)
                implementation(SQLDelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Google.material) // dont think I need this here
                implementation(Ktor.android)
                implementation(Ktor.serializationJvm)
                implementation(Kotlinx.coroutinesAndroid)
                implementation(SQLDelight.androidDriver)

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Junit.core)
            }
        }
        val iosMain by getting {
            dependencies{
                implementation(Ktor.ios)
                implementation(SQLDelight.nativeDriver)
            }
        }
        val iosTest by getting

    }
}

sqldelight {
    database("RecipeDatabase") {
        packageName = "com.example.kmmplayground.cache"
        sourceFolders = listOf("sqldelight")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)





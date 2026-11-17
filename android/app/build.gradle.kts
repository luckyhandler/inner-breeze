plugins {
    id("com.android.application")
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "io.naox.inbe"
    // permission_handler_android 14+ requires API 37 to compile.
    compileSdk = 37
    ndkVersion = flutter.ndkVersion

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        applicationId = "io.naox.inbe"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("FLUTTER_KEY_ALIAS") ?: ""
            keyPassword = System.getenv("FLUTTER_KEY_PASSWORD") ?: ""
            storeFile = file("release-key.jks")
            storePassword = System.getenv("FLUTTER_STORE_PASSWORD") ?: ""
        }
    }

    buildTypes {
        release {
            signingConfig =
                if (file("release-key.jks").exists()) {
                    signingConfigs.getByName("release")
                } else {
                    signingConfigs.getByName("debug")
                }
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

flutter {
    source = "../.."
}

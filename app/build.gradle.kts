plugins {
    id("com.android.application")
    id("picpay-plugin")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    namespace = AppConfig.applicationId

    defaultConfig {
        applicationId = AppConfig.applicationId
        versionCode = AppConfig.appVersionCode
        versionName = AppConfig.appVersionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = " Debug"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //implementation(project(mapOf("path" to ":pdsDesign")))
    koin()
    androidxDesign()
    androidxLifecycle()
    coroutines()
    images()
    retrofit2()
    okhttp3()
    commons()
    //tests
    testLibraries()
    androidTestLibraries()
}
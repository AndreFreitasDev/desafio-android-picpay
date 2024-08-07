plugins {
    id("com.android.application")
    id("picpay-plugin")
    id("kotlin-parcelize")
    kotlin("android")
    id("com.google.devtools.ksp")
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
    // modules
    implementation(project(":modules:dependencyInjection"))
    implementation(project(":layers:presentation"))
    //libs
    koin()
    androidxDesign()
    androidxLifecycle()
    coroutines()
    images()
    retrofit2()
    okhttp3()
    commons()
    timber()
    //tests
    testLibraries()
    androidTestLibraries()
}
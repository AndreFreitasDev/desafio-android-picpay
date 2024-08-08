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
    timber()
    //tests
    androidTestUtil("androidx.test:orchestrator:1.5.0")
    androidTestLibraries()
}
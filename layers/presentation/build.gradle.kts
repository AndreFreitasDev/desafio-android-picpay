plugins {
    id("com.android.library")
    id("picpay-plugin")
}

android {
    namespace = "com.picpay.desafio.modules.presentation"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":modules:commons"))
    implementation(project(":layers:domain"))
    koin()
    androidxDesign()
    androidxLifecycle()
    coroutines()
    images()
    commons()
    timber()
}
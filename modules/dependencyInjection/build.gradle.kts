plugins {
    id("com.android.library")
    id("picpay-plugin")
}

android {
    namespace = "com.picpay.desafio.modules.di"
}

dependencies {
    implementation(project(":layers:data"))
    implementation(project(":layers:domain"))
    implementation(project(":layers:presentation"))
    koin()
}
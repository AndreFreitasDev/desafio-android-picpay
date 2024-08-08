plugins {
    id("com.android.library")
    id("picpay-plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.picpay.desafio.modules.domain"
}

dependencies {
    implementation(project(":modules:commons"))
    implementation(project(":layers:data"))
    koin()
    coroutines()
    testLibraries()
}
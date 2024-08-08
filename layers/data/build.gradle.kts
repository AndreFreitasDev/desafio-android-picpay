plugins {
    id("com.android.library")
    id("picpay-plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.picpay.desafio.modules.data"
}

dependencies {
    androidxRoom()
    koin()
    retrofit2()
    okhttp3()
    timber()
    testLibraries()
}
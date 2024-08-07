plugins {
    id("com.android.library")
    id("picpay-plugin")
}

android {
    namespace = "com.picpay.desafio.libraries.commons"
}

dependencies {
    coroutines()
    androidxAnnotation()
}
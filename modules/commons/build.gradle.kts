plugins {
    id("com.android.library")
    id("picpay-plugin")
}

android {
    namespace = "com.picpay.desafio.modules.commons"
}

dependencies {
    coroutines()
    androidxAnnotation()
}
plugins {
    id("com.android.library")
    id("picpay-plugin")
}

android {
    namespace = "com.picpay.desafio.modules.tests"
}

dependencies {
    implementation(project(":modules:commons"))
    val junit5Version = Versions.junit5_version
    implementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    implementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    implementation("androidx.arch.core:core-testing:${Versions.core_testing_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}")
}
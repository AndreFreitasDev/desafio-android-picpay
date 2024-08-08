import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.androidxAnnotation() {
    implementation("androidx.annotation:annotation:1.3.0")
}

fun DependencyHandler.androidxDesign() {
    implementation("androidx.core:core-ktx:${Versions.core_ktx_version}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat_version}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}")
    implementation("com.google.android.material:material:${Versions.material_version}")
}

fun DependencyHandler.koin() {
    val version = Versions.koin_version
    implementation("io.insert-koin:koin-core:$version")
    implementation("io.insert-koin:koin-android:$version")
    implementation("io.insert-koin:koin-test:$version")
}

fun DependencyHandler.androidxLifecycle() {
    val version = Versions.lifecycle_version
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}

fun DependencyHandler.coroutines() {
    val version = Versions.coroutines_version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$version")
}

fun DependencyHandler.images() {
    implementation("com.squareup.picasso:picasso:${Versions.picasso_version}")
    implementation("de.hdodenhof:circleimageview:${Versions.circleimageview_version}")
}

fun DependencyHandler.timber() {
    implementation("com.jakewharton.timber:timber:5.0.1")
}

fun DependencyHandler.androidxRoom() {
    val version = Versions.room_version
    ksp("androidx.room:room-compiler:$version")
    implementation("androidx.room:room-runtime:$version")
    implementation("androidx.room:room-ktx:$version")
    testImplementation("androidx.room:room-testing:$version")
}

fun DependencyHandler.retrofit2() {
    val version = Versions.retrofit_version
    implementation("com.squareup.retrofit2:retrofit:$version")
    implementation("com.squareup.retrofit2:converter-gson:$version")
}

fun DependencyHandler.okhttp3() {
    val version = Versions.okhttp_version
    implementation("com.squareup.okhttp3:okhttp:$version")
    implementation("com.squareup.okhttp3:mockwebserver:$version")
    implementation("com.squareup.okhttp3:logging-interceptor:$version")
}

fun DependencyHandler.commons() {
    implementation("com.google.code.gson:gson:2.8.6")
}

fun DependencyHandler.testLibraries() {
    // jUnit4
    testImplementation("junit:junit:${Versions.junit4_version}")
    // jUnit5
    val junit5Version = Versions.junit5_version
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junit5Version")

    // coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}")

    // mockk
    val mockkVersion = Versions.mockk_version
    testImplementation("io.mockk:mockk-android:$mockkVersion")
    testImplementation("io.mockk:mockk-agent:$mockkVersion")

    // flow
    testImplementation("app.cash.turbine:turbine:1.0.0")

    // Assertions
    testImplementation("com.google.truth:truth:1.1.3")

    //others
    testImplementation("androidx.arch.core:core-testing:${Versions.core_testing_version}")
    testImplementation("org.mockito:mockito-core:${Versions.mockito_version}")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}")

    // module
    testImplementation(project(":modules:tests"))
}

fun DependencyHandler.androidTestLibraries() {
    androidTestImplementation("androidx.test:runner:${Versions.test_runner_version}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso_version}")
    androidTestImplementation("androidx.test:core-ktx:${Versions.core_ktx_test_version}")
}
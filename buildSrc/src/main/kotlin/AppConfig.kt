import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion

object AppConfig {

    const val minSdkVersion = 21
    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val buildToolsVersion = "34.0.0"

    // App constants
    const val applicationId = "com.picpay.desafio.android"
    const val appVersionName = "1.0.0"
    const val appVersionCode = 1

    const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"

    val targetCompatibility = JavaVersion.VERSION_11
    val sourceCompatibility = JavaVersion.VERSION_11
    val javaLanguageVersion = JavaLanguageVersion.of("11")
}
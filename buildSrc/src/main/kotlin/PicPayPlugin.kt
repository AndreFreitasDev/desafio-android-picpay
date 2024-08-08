import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class PicPayPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                when (this) {
                    is AppExtension -> configureAndroidApplication(project, this)
                    is LibraryExtension -> configureAndroidLibrary(project, this)
                }
            }
        }
    }

    private fun configureAndroidApplication(project: Project, android: AppExtension) {
        // Apply Required Plugins.
        project.plugins.apply {
            apply("org.jetbrains.kotlin.android")
            apply("de.mannodermaus.android-junit5")
        }

        // Configure common android build parameters.
        android.apply {
            compileSdkVersion(AppConfig.compileSdkVersion)
            buildToolsVersion = AppConfig.buildToolsVersion

            defaultConfig {
                minSdk = AppConfig.minSdkVersion
                targetSdk = AppConfig.targetSdkVersion
                //testInstrumentationRunner = AppConfig.androidJUnitRunner
                multiDexEnabled = true
                vectorDrawables.useSupportLibrary = true
            }
            compileOptions {
                sourceCompatibility = AppConfig.sourceCompatibility
                targetCompatibility = AppConfig.targetCompatibility
            }
        }
        project.applyToolchain()
        project.applyJvmToolchain()
    }

    private fun configureAndroidLibrary(project: Project, android: LibraryExtension) {
        // Apply Required Plugins.
        project.plugins.apply {
            apply("org.jetbrains.kotlin.android")
            apply("de.mannodermaus.android-junit5")
        }

        // Configure common android build parameters.
        android.apply {
            compileSdk = AppConfig.compileSdkVersion

            defaultConfig {
                minSdk = AppConfig.minSdkVersion
                //targetSdk = AppConfig.targetSdkVersion
            }
            buildTypes {
                getByName("release")
                getByName("debug")
            }
            compileOptions {
                sourceCompatibility = AppConfig.sourceCompatibility
                targetCompatibility = AppConfig.targetCompatibility
            }
        }
        project.applyToolchain()
        project.applyJvmToolchain()
    }

    private fun Project.applyJvmToolchain() {
        val kotlin = this.extensions.getByName("kotlin")
        if (kotlin is KotlinAndroidProjectExtension) {
            kotlin.jvmToolchain {
                languageVersion.set(AppConfig.javaLanguageVersion)
            }
        }
    }

    private fun Project.applyToolchain() {
        val java = this.extensions.getByName("java")
        if (java is JavaPluginExtension) {
            java.toolchain {
                languageVersion.set(AppConfig.javaLanguageVersion)
            }
        }
    }
}
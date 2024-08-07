buildscript {
    repositories {
        google()
        mavenCentral()
    }

    val buildGradleToolsVersion = extra["buildGradleToolsVersion"]
    val kotlinVersion = extra["kotlinVersion"]

    dependencies {
        classpath("com.android.tools.build:gradle:$buildGradleToolsVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.10.0.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.layout.buildDirectory)
    }
}
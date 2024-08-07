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

plugins {
    val kspVersion = "1.9.24-1.0.20"
    id("com.google.devtools.ksp") version kspVersion apply false
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
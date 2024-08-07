import org.gradle.kotlin.dsl.`kotlin-dsl`
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        register("picpay-plugin") {
            id = "picpay-plugin"
            implementationClass = "PicPayPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    val properties = getPropertiesFile()
    val buildGradleToolsVersion = properties.getProperty("buildGradleToolsVersion")
    val kotlinVersion = properties.getProperty("kotlinVersion")
    implementation("com.android.tools.build:gradle:$buildGradleToolsVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation(gradleApi())
    implementation(localGroovy())
}

fun Project.getPropertiesFile(file: String = "gradle.properties"): Properties {
    val rootProject = project.rootDir.parentFile
    val properties = Properties()
    val localProperties = File(rootProject, file)
    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("File from not found")

    return properties
}
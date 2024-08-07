import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.ksp(dependency: String) : Dependency? =
    add("ksp", dependency)

fun DependencyHandler.testImplementation(dependency: String): Dependency? =
    add("testImplementation", dependency)

fun DependencyHandler.androidTestImplementation(dependency: String): Dependency? =
    add("androidTestImplementation", dependency)

fun DependencyHandler.testRuntimeOnly(dependency: String): Dependency? =
    add("testRuntimeOnly", dependency)
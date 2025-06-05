import gradle.kotlin.dsl.accessors._38e0a2dcecfc59693fbb8bbc91bb220f.sourceSets
import org.gradle.accessors.dm.LibrariesForLibs

// Нужен, т.к. модуль utils-compose не может имплементировать сам себя

val libs = the<LibrariesForLibs>()
plugins {
    id("multiplatform-setup")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            runtimeOnly(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)

            implementation(libs.decompose.compose)
            implementation(libs.decompose.core)
        }
    }
}

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.binaryen.BinaryenRootEnvSpec
import org.jetbrains.kotlin.gradle.targets.js.binaryen.BinaryenRootPlugin

plugins {
    //trick: for the same plugin versions in all sub-modules
    id(libs.plugins.android.application.get().pluginId) apply false
    id(libs.plugins.kotlin.get().pluginId) apply false
    id(libs.plugins.compose.mpp.get().pluginId) apply false
    id(libs.plugins.compose.plugin.get().pluginId) apply false
    id(libs.plugins.ksp.get().pluginId) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

apply<BinaryenRootPlugin>()
@OptIn(ExperimentalWasmDsl::class)
the<BinaryenRootEnvSpec>().version.set("123")
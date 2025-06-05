import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

val libs = the<LibrariesForLibs>()

// couldn't use `libs` here =/
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("web") {
                withJs()
                withWasmJs()
            }
        }
    }

    androidTarget()

    js(IR) {
        this.binaries.library()
        browser()
    }


    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        this.binaries.library()
        browser()
    }

    jvmToolchain(Config.Java.intVersion)
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        @Suppress("DEPRECATION")
        kotlinOptions.jvmTarget = Config.Java.stringVersion
    }
}

android {
    namespace = Config.Android.getConventionPluginNameSpace(null)
    compileSdk = Config.Android.compileSdk
    defaultConfig {
        minSdk = Config.Android.minSdk
    }

    sourceSets {
        named("main") {
            res.srcDirs("src/main/res")
        }
    }
}
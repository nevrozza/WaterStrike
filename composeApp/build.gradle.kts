import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.get().pluginId)
    id(libs.plugins.compose.plugin.get().pluginId)
    id(libs.plugins.serialization.get().pluginId)
    id(libs.plugins.compose.mpp.get().pluginId)
}

version = Config.Application.versionName


val jsAppName = project.name+"-js"
val wasmAppName = project.name+"-wasm"

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate() {
        common {
            group("web") {
                withJs()
                withWasmJs()
            }
        }
    }

    androidTarget()

    @OptIn(ExperimentalWasmDsl::class)
    listOf(
        js(IR) to jsAppName,
        wasmJs() to wasmAppName
    ).forEach { (target, appName) ->
        target.compilations.all {
            @Suppress("DEPRECATION")
            kotlinOptions {
                freeCompilerArgs += listOf("-Xir-minimized-member-names")
            }
        }
        target.browser {
            commonWebpackConfig {

                mode = KotlinWebpackConfig.Mode.PRODUCTION
                outputFileName = "$appName.js"
            }
        }
        target.binaries.executable()
    }


    sourceSets {
        commonMain.dependencies {
            runtimeOnly(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)

            implementation(libs.decompose.compose)
            implementation(libs.decompose.core)

            implementation(libs.mvikotlin.core)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin.coroutines)


            implementation(libs.settings.core)
            implementation(libs.settings.no.arg)

            implementation(project(Modules.core))
            implementation(project(Modules.di))
            implementation(project(Modules.utilsCompose))
            implementation(project(Modules.utils))

            implementation(project(Modules.Main.compose))
            implementation(project(Modules.Main.presentation))
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.accompanist.systemuicontroller)
            implementation(libs.koin.android)
        }
    }


    jvmToolchain(17)

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        @Suppress("DEPRECATION")
        kotlinOptions.jvmTarget = "17"
    }
}

android {
    sourceSets {
        getByName("main") {
            manifest.srcFile ("src/androidMain/AndroidManifest.xml")
        }
    }

    namespace = Config.Android.namespace
    compileSdk = Config.Android.compileSdk

    defaultConfig {
        applicationId = Config.Android.namespace
        minSdk = Config.Android.minSdk
        targetSdk = Config.Android.targetSdk
        versionCode = Config.Application.versionCode
        versionName = version.toString()
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            this.matchingFallbacks.add("release")
            this.isMinifyEnabled = true
//            proguardFile("proguard-rules-android.pro") TODO
        }
        debug {
            isDebuggable = true
            this.matchingFallbacks.add("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// https://github.com/JetBrains/kotlinconf-app/blob/f61983404304a4d89981e7a2a2d26ff522406d44/shared/build.gradle.kts
val buildWebApp by tasks.registering(Copy::class) {
    val wasmDist = "wasmJsBrowserDistribution"
    val jsDist = "jsBrowserDistribution"
    val dir = "prodWebApp"

    buildWebApplication(
        wasmDist, jsDist, dir
    )
}

val buildDevWebApp by tasks.registering(Copy::class) {
    val wasmDist = "wasmJsBrowserDevelopmentExecutableDistribution"
    val jsDist = "jsBrowserDevelopmentExecutableDistribution"
    val dir = "devWebApp"
    buildWebApplication(
        wasmDist, jsDist, dir
    )
}

fun Copy.buildWebApplication(
    wasmDist: String,
    jsDist: String,
    dirName: String
) {

    val dir = layout.buildDirectory.dir(dirName)

    dependsOn(wasmDist, jsDist)

    from(tasks.named(jsDist).get().outputs.files)
    from(tasks.named(wasmDist).get().outputs.files)

    val dirFile = dir.get().asFile
    if (dirFile.exists()) {
        dirFile.listFiles { f ->
            project.delete(f)
        }
    }

    into(dir)

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    doLast {
        val currentTime = System.currentTimeMillis()

        val enableWasmFile = destinationDir.resolve("enable_wasm.js")
        if (enableWasmFile.exists()) {
            var newEnableWasmText = enableWasmFile.readText()
            val versionedFilesNames = listOf("composeApp-wasm.js", "skiko.js", "composeApp-js.js")

            for (fileName in versionedFilesNames) {
                newEnableWasmText = newEnableWasmText.replace(fileName, "$fileName?v=$currentTime")
            }
            enableWasmFile.writeText(
                newEnableWasmText
            )
        }
    }
}
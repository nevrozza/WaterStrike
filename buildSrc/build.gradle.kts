
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.gradle.plugin.kotlin.android)
    implementation(libs.gradle.plugin.android)
    implementation(libs.gradle.plugin.compose)
    implementation(libs.gradle.plugin.compose.compiler)
    implementation(libs.gradle.plugin.serialization)
    implementation(libs.gradle.plugin.ksp)


    //https://stackoverflow.com/a/70878181
    // An error in the editor, although the dependencies are correctly resolved.
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

kotlin {
    jvmToolchain(17)
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
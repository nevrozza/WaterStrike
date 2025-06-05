import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
plugins {
    id("logic-internal-setup")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.settings.core)
        }
    }
}
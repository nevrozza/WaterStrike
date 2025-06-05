import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
plugins {
    id("compose-common-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.utilsCompose))
            implementation(libs.koin.core)
        }
    }
}
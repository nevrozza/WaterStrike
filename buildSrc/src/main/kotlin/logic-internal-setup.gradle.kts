import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()
plugins {
    id("multiplatform-setup")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.core))
            implementation(project(Modules.utils))
        }
    }
}
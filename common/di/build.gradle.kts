plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.core))
            implementation(project(Modules.Main.domain))
            implementation(project(Modules.Main.data))
            implementation(project(Modules.Main.presentation))

            implementation(libs.settings.core)
        }
    }
}
plugins {
    id("data-settings-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Main.domain))
        }
    }
}
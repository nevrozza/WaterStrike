plugins {
    id("presentation-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(Modules.Main.domain))
        }
    }
}
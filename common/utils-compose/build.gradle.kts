plugins {
    id("compose-common-setup")
}



kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(Modules.utils))

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
        }
    }
}


plugins {
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // koin
            api(libs.koin.core)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.client.logging)

            // russhwolf settings
            implementation(libs.settings.core)
            implementation(libs.settings.no.arg)
        }
    }
}
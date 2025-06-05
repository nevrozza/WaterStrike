plugins {
    id("compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.Main.presentation))
        }
    }
}

android {
    namespace = Config.Android.getConventionPluginNameSpace(Modules.Main.compose)
}
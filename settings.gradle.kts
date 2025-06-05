enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Water_Strike"
//include(":androidApp")


val commonPath = ":common"

include("composeApp")

include("$commonPath:core")
include("$commonPath:di")
include("$commonPath:utils")
include("$commonPath:utils-compose")

val modules = listOf("main", "match-list", "game")

modules.forEach { module ->
    val subModules = listOf("data", "domain", "presentation", "compose")
    subModules.forEach { subModule ->
        include("$commonPath:$module:$subModule")
    }
}
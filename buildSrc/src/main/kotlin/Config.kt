@file:Suppress(
//    "MayBeConstant",
    "SpellCheckingInspection",
    "MemberVisibilityCanBePrivate",
    "ConstPropertyName"
)

import org.gradle.api.JavaVersion


object Config {
    object Application {
        const val versionCode = 1
        const val versionName = "1.0"
    }
    object Android {
        const val namespace = "com.nevrozq.waterstrike"

        const val compileSdk = 35
        const val minSdk = 26
        const val targetSdk = 35

        fun getConventionPluginNameSpace(module: String?): String {
            val conventionPluginName = "$namespace.convention"
            if (module == null) {
                return conventionPluginName
            }
            val formattedModuleName = module
                .replace(Modules.commonPath, "")
                .replace(Regex("[:-]"), ".")
            return "$conventionPluginName$formattedModuleName"
        }
    }

    object Java {
        val version = JavaVersion.VERSION_17
        val stringVersion = version.majorVersion
        val intVersion = stringVersion.toInt()
    }
}


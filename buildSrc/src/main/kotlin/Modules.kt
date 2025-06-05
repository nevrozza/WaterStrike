@file:Suppress("ConstPropertyName")

object Modules {
    const val composeApp = "composeApp"


    const val commonPath = ":common"

    object Main {
        private const val modulePath = "$commonPath:main"
        const val data = "$modulePath:data"
        const val domain = "$modulePath:domain"
        const val presentation = "$modulePath:presentation"
        const val compose = "$modulePath:compose"
    }

    object MatchList {
        private const val modulePath = "$commonPath:match-list"
        const val data = "$modulePath:data"
        const val domain = "$modulePath:domain"
        const val presentation = "$modulePath:presentation"
        const val compose = "$modulePath:compose"
    }

    object Game {
        private const val modulePath = "$commonPath:game"
        const val data = "$modulePath:data"
        const val domain = "$modulePath:domain"
        const val presentation = "$modulePath:presentation"
        const val compose = "$modulePath:compose"
    }

    const val core = "$commonPath:core"
    const val di = "$commonPath:di"
    const val utils = "$commonPath:utils"
    const val utilsCompose = "$commonPath:utils-compose"
}
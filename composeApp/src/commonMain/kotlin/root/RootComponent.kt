package root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.serialization.Serializable
import main.MainComponent

interface RootComponent: BackHandlerOwner {
    val stack: Value<ChildStack<Config, Child>>
    val nav: StackNavigation<Config>

    sealed class Child {
        data class MainChild(val component: MainComponent) : Child()
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object MainScreen : Config
    }

    fun onBackClicked()

}
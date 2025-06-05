package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import main.MainComponent
import root.RootComponent.Child
import root.RootComponent.Child.MainChild
import root.RootComponent.Config
import root.RootComponent.Config.MainScreen
import root.outputs.onMainOutput
import kotlin.reflect.KClass

class RootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory
) : RootComponent, ComponentContext by componentContext {

    override val nav = StackNavigation<Config>()

    private val _stack: Value<ChildStack<Config, Child>>
        get() = childStack(
            source = nav,
            serializer = Config.serializer(),
            initialConfiguration = MainScreen,
            childFactory = ::child
        )

    override val stack = _stack

    private fun child(config: Config, childContext: ComponentContext) : Child {
        return when(config) {
            MainScreen -> MainChild(
                MainComponent(
                    componentContext =  childContext,
                    storeFactory = storeFactory,
                    output = ::onMainOutput
                )
            )
        }
    }


    override fun onBackClicked() {
        popOnce(stack.active.instance::class)
    }

    private fun popOnce(
        child: KClass<out Child>
    ) {
        if (child.isInstance(stack.active.instance)) {
            nav.pop()
        }

    }

}
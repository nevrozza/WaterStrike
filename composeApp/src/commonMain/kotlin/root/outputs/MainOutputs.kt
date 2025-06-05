package root.outputs

import com.arkivanov.decompose.router.stack.bringToFront
import main.MainComponent
import root.RootComponent

fun RootComponent.onMainOutput(
    output: MainComponent.Output
) {
    when (output) {
        MainComponent.Output.NavigateToMatchList -> TODO("DAMN")
    }
}
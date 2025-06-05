package compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import main.MainScreen
import root.RootComponent


@OptIn(ExperimentalDecomposeApi::class)
fun getDefaultAnimation( //<config: RootComponent.Config, child: RootComponent.Child>
    component: RootComponent
): StackAnimation<RootComponent.Config, RootComponent.Child> = predictiveBackAnimation(
    backHandler = component.backHandler,
    fallbackAnimation = stackAnimation(),
    onBack = component::onBackClicked
)

@Composable
fun DestinationsScreen(
    stack: ChildStack<RootComponent.Config, RootComponent.Child>,
    animation: StackAnimation<RootComponent.Config, RootComponent.Child>? = null
) {
    Children(
        stack = stack,
        animation = animation,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.MainChild -> MainScreen(child.component)
        }
    }
}
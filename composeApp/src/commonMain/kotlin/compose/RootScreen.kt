package compose

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import root.RootComponent

@Composable
fun RootScreen(
    component: RootComponent
) {
    val stack by component.stack.subscribeAsState()

    Scaffold { _ ->
        DestinationsScreen(
            stack = stack,
            animation = getDefaultAnimation(component)
        )
    }
}
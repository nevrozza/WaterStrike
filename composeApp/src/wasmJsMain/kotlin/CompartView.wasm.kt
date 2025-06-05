import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
@Suppress("FunctionName")
actual fun CompatView(
    content: @Composable () -> Unit
) {
    ComposeViewport("composeApp") {
        content()
    }
}
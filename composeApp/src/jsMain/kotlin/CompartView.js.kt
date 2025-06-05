import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
@Suppress("FunctionName")
actual fun CompatView(
    content: @Composable () -> Unit
) {
    onWasmReady {
        ComposeViewport("composeApp") {
            content()
        }
    }
}
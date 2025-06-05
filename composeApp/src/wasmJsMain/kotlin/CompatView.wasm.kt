import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.window
import kotlinx.coroutines.await
import view.compositionLocals.LocalBottomWebPadding

@OptIn(ExperimentalComposeUiApi::class)
@Suppress("FunctionName")
actual fun CompatView(
    content: @Composable () -> Unit
) {

    val bottomPaddingWeb = mutableStateOf(0.0f)

    val sizeManager = SizeManager().apply {
        resize()
    }

    CanvasBasedWindow(
        canvasElementId = "composeApp",
        applyDefaultStyles = false,
        requestResize = {
            val size = sizeManager.getChanges().await<Size>()
            val height = window.innerHeight

            bottomPaddingWeb.value = (height - size.height) * window.devicePixelRatio.toFloat()

            IntSize(width = window.innerWidth, height = height)
        }
    ) {
        CompositionLocalProvider(
            LocalBottomWebPadding provides bottomPaddingWeb.value
        ) {
            content()
        }
    }
}
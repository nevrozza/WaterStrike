import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import compose.RootScreen
import root.RootComponentImpl
import view.ThemeTint
import view.compositionLocals.LocalViewManager
import view.compositionLocals.initViewManager
import view.compositionLocals.viewManagerState
import view.theme.AppTheme
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@JsName("webMain")
fun main() {

    val deviceId = Uuid.parse(getOrCreateDeviceUUID())
    initKoin(
        platformConfiguration = PlatformConfiguration(
            deviceId = deviceId
        ),
        enableLogging = true
    )

    val lifecycle = LifecycleRegistry()

    val rootComponent = RootComponentImpl(
        componentContext = DefaultComponentContext(
            lifecycle = lifecycle
        ),
        storeFactory = DefaultStoreFactory()
    )
    viewManagerState = initViewManager()
    CompatView {
        PageLoadNotify()
        CompositionLocalProvider(
            LocalViewManager provides viewManagerState
        ) {
            AppTheme {
                RootScreen(rootComponent)
            }
        }
    }
}
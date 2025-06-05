import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

external fun onLoadFinished()

@Composable
fun PageLoadNotify() {
    LaunchedEffect(Unit) {
        onLoadFinished()
    }
}


private const val DEVICE_UUID_KEY = "deviceUUIDKey"

@OptIn(ExperimentalUuidApi::class)
fun getOrCreateDeviceUUID(): String {
    val settings = Settings()
    val storedUUID: String? = settings[DEVICE_UUID_KEY]
    return if (storedUUID != null) {
        storedUUID
    } else {
        val newUUID = Uuid.random().toString()
        settings[DEVICE_UUID_KEY] = newUUID
        newUUID
    }
}
package decompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.shareIn

@Composable
fun <Intent : Any, State : Any, Label : Any> DefaultMVIComponent<Intent, State, Label>.subscribeOnLabels(
    action: suspend (Label) -> Unit
) {
    LaunchedEffect(this) {
        labels.collectLatest(action)
    }
}

@Composable
fun <Intent : Any, State : Any, Label : Any> DefaultMVIComponent<Intent, State, Label>.subscribeOnLabels(
    onSubscription: () -> Unit = {},
    action: suspend (Label) -> Unit
) {
    LaunchedEffect(this) {
        /*
        Unfortunately, labels may arrive before the subscription (especially on the web),
        so we should use SharedFlow instead of Flow (since it has an `onSubscription`)
         */
        labels.shareIn(this, started = SharingStarted.Eagerly)
            .onSubscription { onSubscription() }.collectLatest(action)
    }
}

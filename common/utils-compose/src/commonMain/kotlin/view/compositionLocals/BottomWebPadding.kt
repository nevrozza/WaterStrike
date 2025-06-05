package view.compositionLocals

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

val LocalBottomWebPadding = compositionLocalOf { 0f }


@Composable
fun Modifier.webPadding(): Modifier {
    val density = LocalDensity.current
    val bottomWebPadding = LocalBottomWebPadding.current

    return this.padding(
        bottom = animateDpAsState(
            with(density) { bottomWebPadding.toDp() },
            animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
        ).value
    )
}
package view.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import animations.animated
import view.compositionLocals.viewManagerState
import view.consts.Typography

@Composable
fun AppTheme(content: @Composable () -> Unit) {


    viewManagerState = viewManagerState.copy(isDark = isThemeDark(viewManagerState.tint))

    val colorScheme = if (viewManagerState.isDark)
        darkColorScheme() else lightColorScheme()

    MaterialTheme(
        colorScheme = colorScheme.animated(remember { spring(stiffness = Spring.StiffnessVeryLow) }),
        typography = Typography
    ) {
        content()
    }
}


package view.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import animations.animated
import view.compositionLocals.viewManagerState
import view.consts.Typography
import view.viewManager

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    viewManagerState = with(viewManagerState) {
        this.copy(
            isDark = isThemeDark(this.tint)
        )
    }


    val colorScheme = if (viewManager.isDark)
        darkColorScheme() else lightColorScheme()

    CompositionLocalProvider(
        // fix xiaomi dark theme with dark color
        LocalContentColor provides colorScheme.onBackground
    ) {
        MaterialTheme(
            colorScheme = colorScheme.animated(
                remember { spring(stiffness = Spring.StiffnessVeryLow) }
            ),
            typography = Typography
        ) {
            content()
        }
    }
}


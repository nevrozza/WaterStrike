package view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import view.ThemeTint

@Composable
fun isThemeDark(tint: ThemeTint) =
        if (tint == ThemeTint.Auto) isSystemInDarkTheme()
        else tint == ThemeTint.Dark
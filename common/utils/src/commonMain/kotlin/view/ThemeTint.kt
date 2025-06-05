package view

enum class ThemeTint {
    Auto, Dark, Light
}

fun getThemeFromName(themeTintName: String): ThemeTint = when (themeTintName) {
    ThemeTint.Dark.name -> ThemeTint.Dark
    ThemeTint.Light.name -> ThemeTint.Light
    else -> ThemeTint.Auto
}

package com.nevrozq.waterstrike

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import view.compositionLocals.ViewManager
import view.theme.isThemeDark

@SuppressLint("ComposableNaming")
@Composable
fun setSystemBarsColor(viewManager: ViewManager) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = !isThemeDark(viewManager.tint),
        isNavigationBarContrastEnforced = false
    )
}
package com.nevrozq.waterstrike

import PlatformConfiguration
import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import compose.RootScreen
import initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import root.RootComponentImpl
import usecases.ThemeUseCases
import view.compositionLocals.LocalViewManager
import view.compositionLocals.initViewManager
import view.compositionLocals.viewManagerState
import view.theme.AppTheme
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalUuidApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenSetup()

        @SuppressLint("HardwareIds")
        val deviceId = Uuid.fromByteArray(
            Settings.Secure.getString(
                contentResolver,
                Settings.Secure.ANDROID_ID
            ).encodeToByteArray()
        )

        val koin = initKoin(
            platformConfiguration = PlatformConfiguration(deviceId),
            enableLogging = (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0 // isDebug check
        ) {
            androidContext(applicationContext)
        }

        val rootComponent = RootComponentImpl(
            componentContext = defaultComponentContext(),
            storeFactory = DefaultStoreFactory()
        )

        val theme = koin.koin.get<ThemeUseCases>().getTheme().name
        viewManagerState = initViewManager(theme)

        setContent {
            CompositionLocalProvider(
                LocalViewManager provides viewManagerState
            ) {
                AppTheme {
                    setSystemBarsColor(viewManagerState)
                    RootScreen(rootComponent)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}
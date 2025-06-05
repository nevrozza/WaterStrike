package main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import base.DefaultTextField
import decompose.subscribeOnLabels
import main.MainComponent.Output
import view.ThemeTint
import view.compositionLocals.LocalViewManager
import view.compositionLocals.viewManagerState
import view.consts.Paddings
import view.typography

@Composable
fun MainScreen(
    component: MainComponent
) {
    component.subscribeOnLabels(
        onSubscription = { component.onEvent(MainStore.Intent.UpdateTheme) }
    ) { label ->
        when (label) {
            is MainStore.Label.UpdateTheme -> {
                viewManagerState = viewManagerState.copy(tint = label.theme)
            }
        }
    }

    MainContent(component)
}

@Composable
private fun MainContent(
    component: MainComponent
) {
    val model by component.model.subscribeAsState()
    val viewManager = LocalViewManager.current

    val themes = remember {
        listOf(
            ThemeTint.Light to "Светлая",
            ThemeTint.Auto to "Авто",
            ThemeTint.Dark to "Тёмная"
        )
    }

    Scaffold(
        Modifier.fillMaxSize()
    ) { paddings ->
        Column(Modifier.padding(horizontal = Paddings.medium).padding(paddings).imePadding()) {
            Box(Modifier.fillMaxHeight(.35f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    "Water Strike", style = typography.headlineLarge,
                    modifier = Modifier
                )
            }
            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultTextField(
                    value = model.nickname,
                    onValueChange = { text ->
                        component.onEvent(MainStore.Intent.ChangeNickname(text))
                    },
                    placeholderText = "Ник",
                    paddingBetween = Paddings.zero,
                    contentPadding = PaddingValues(Paddings.medium),
                    modifier = Modifier.defaultMinSize(minWidth = 200.dp)
                )
                Spacer(Modifier.height(Paddings.medium))
                DefaultTextField(
                    value = model.host,
                    onValueChange = { text ->
                        component.onEvent(MainStore.Intent.ChangeHost(text))
                    },
                    placeholderText = "Хост",
                    paddingBetween = Paddings.zero,
                    contentPadding = PaddingValues(Paddings.medium),
                    modifier = Modifier.defaultMinSize(minWidth = 200.dp)
                )
                Spacer(Modifier.height(Paddings.medium))

                SingleChoiceSegmentedButtonRow {
                    themes.forEachIndexed { index, (themeTint, label) ->
                        SegmentedButton(
                            selected = viewManager.tint == themeTint,
                            onClick = {
                                component.onEvent(MainStore.Intent.ChangeTheme(themeTint))
                            },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = themes.size
                            ),
                            label = { Text(label) },
                            icon = {}
                        )
                    }
                }

                Spacer(Modifier.height(Paddings.medium))

                Button(
                    onClick = {
                        component.onOutput(Output.NavigateToMatchList)
                    },
                    contentPadding = PaddingValues(
                        horizontal = Paddings.large,
                        vertical = Paddings.small
                    )
                ) {
                    Text("Играть")
                }
                Spacer(Modifier.height(Paddings.large))
            }
        }
    }
}
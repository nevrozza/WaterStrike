package main

import com.arkivanov.mvikotlin.core.store.Store
import main.MainStore.Intent
import main.MainStore.State
import main.MainStore.Label
import view.ThemeTint

interface MainStore: Store<Intent, State, Label> {

    data class State(
        val nickname: String = "",
        val host: String = ""
    )

    sealed interface Intent {
        data class ChangeNickname(val nickname: String) : Intent
        data class ChangeHost(val host: String) : Intent
        data class ChangeTheme(val theme: ThemeTint) : Intent
        data object UpdateTheme : Intent
    }

    sealed interface Message {
        data class NicknameChanged(val nickname: String) : Message
        data class HostChanged(val host: String) : Message
    }

    sealed interface Label {
        data class UpdateTheme(val theme: ThemeTint) : Label
    }


}
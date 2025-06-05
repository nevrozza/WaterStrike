package main

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import main.MainStore.Intent
import main.MainStore.Label
import main.MainStore.Message
import main.MainStore.State
import usecases.HostUseCases
import usecases.NicknameUseCases
import usecases.ThemeUseCases
import view.ThemeTint

class MainExecutor(
    private val hostUseCases: HostUseCases,
    private val nicknameUseCases: NicknameUseCases,
    private val themeUseCases: ThemeUseCases,
) : CoroutineExecutor<Intent, Unit, State, Message, Label>() {

    override fun executeAction(action: Unit) {
        initData()
    }

    private fun initData() {
        scope.launch {
            val nickname = nicknameUseCases.getNickname()
            val host = hostUseCases.getHost()
            dispatch(Message.NicknameChanged(nickname))
            dispatch(Message.HostChanged(host))
        }
    }

    override fun executeIntent(intent: Intent) {
        when(intent) {
            is Intent.ChangeNickname -> changeNickname(intent.nickname)
            is Intent.ChangeHost -> changeHost(intent.host)
            is Intent.ChangeTheme -> changeTheme(intent.theme)
            Intent.UpdateTheme -> updateTheme()
        }
    }

    private fun updateTheme() {
        scope.launch {
            val theme = themeUseCases.getTheme()
            publish(Label.UpdateTheme(theme))
        }
    }

    private fun changeTheme(theme: ThemeTint) {
        scope.launch {
            publish(Label.UpdateTheme(theme))
            themeUseCases.saveTheme(theme)
        }
    }

    private fun changeNickname(nickname: String) {
        if (nickname.length <= 20) {
            dispatch(Message.NicknameChanged(nickname))
            scope.launch {
                nicknameUseCases.saveNickname(nickname)
            }
        }
    }

    private fun changeHost(host: String) {
        dispatch(Message.HostChanged(host))
        scope.launch {
            hostUseCases.saveHost(host)
        }
    }
}
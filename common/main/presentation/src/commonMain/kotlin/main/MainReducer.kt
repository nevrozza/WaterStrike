package main

import com.arkivanov.mvikotlin.core.store.Reducer

object MainReducer : Reducer<MainStore.State, MainStore.Message> {
    override fun MainStore.State.reduce(msg: MainStore.Message): MainStore.State {
        return when (msg) {
            is MainStore.Message.NicknameChanged -> copy(nickname = msg.nickname)
            is MainStore.Message.HostChanged -> copy(host = msg.host)
        }
    }
}
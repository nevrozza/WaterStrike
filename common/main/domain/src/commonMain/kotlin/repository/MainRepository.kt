package repository

import view.ThemeTint

interface MainRepository {
    fun saveNickname(nickname: String)
    fun getNickname() : String

    fun saveHost(host: String)
    fun getHost() : String

    fun saveTheme(theme: ThemeTint)
    fun getTheme() : ThemeTint
}
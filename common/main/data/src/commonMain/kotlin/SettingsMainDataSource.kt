import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import view.ThemeTint

interface SettingsMainDataSource {
    fun saveHost(host: String)
    fun getHost(): String
    fun saveNickname(nickname: String)
    fun getNickname(): String
    fun saveTheme(theme: String)
    fun getTheme(): String
}

class SettingsMainDataSourceImpl(
    private val settings: Settings
) : SettingsMainDataSource {
    override fun saveHost(host: String) {
        settings[HOST_KEY] = host
    }
    override fun getHost() = settings[HOST_KEY, "0.0.0.0:8080"]


    override fun saveNickname(nickname: String) {
        settings[NICKNAME_KEY] = nickname
    }
    override fun getNickname() = settings[NICKNAME_KEY, ""]


    override fun saveTheme(theme: String) {
        settings[THEME_KEY] = theme
    }
    override fun getTheme(): String = settings[THEME_KEY, ThemeTint.Auto.name]

    companion object {
        const val HOST_KEY = "hostKey"
        const val NICKNAME_KEY = "nicknameKey"
        const val THEME_KEY = "themeKey"
    }
}
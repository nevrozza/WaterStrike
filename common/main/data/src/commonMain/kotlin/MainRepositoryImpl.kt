import repository.MainRepository
import view.ThemeTint
import view.getThemeFromName

class MainRepositoryImpl(
    private val localDataSource: SettingsMainDataSource
) : MainRepository {
    override fun saveNickname(nickname: String) = localDataSource.saveNickname(nickname)

    override fun getNickname(): String = localDataSource.getNickname()

    override fun saveHost(host: String) = localDataSource.saveHost(host)
    override fun getHost(): String = localDataSource.getHost()

    override fun saveTheme(theme: ThemeTint) = localDataSource.saveTheme(theme.name)
    override fun getTheme(): ThemeTint = getThemeFromName(localDataSource.getTheme())
}
import main.MainExecutor
import org.koin.dsl.module
import repository.MainRepository
import usecases.HostUseCases
import usecases.HostUseCasesImpl
import usecases.NicknameUseCases
import usecases.NicknameUseCasesImpl
import usecases.ThemeUseCases
import usecases.ThemeUseCasesImpl

internal val mainModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }

    factory<SettingsMainDataSource> { SettingsMainDataSourceImpl(get()) }

    factory<NicknameUseCases> { NicknameUseCasesImpl(get()) }
    factory<HostUseCases> { HostUseCasesImpl(get()) }
    factory<ThemeUseCases> { ThemeUseCasesImpl(get()) }

    factory<MainExecutor> {
        MainExecutor(
            hostUseCases = get(),
            nicknameUseCases = get(),
            themeUseCases = get()
        )
    }
}
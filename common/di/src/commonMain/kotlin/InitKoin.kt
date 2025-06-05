import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(
    platformConfiguration: PlatformConfiguration,
    enableLogging: Boolean = false,
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication {
    val platformModule = module {
        single {
            platformConfiguration
        }
    }
    return startKoin {
        appDeclaration()

        modules(
            platformModule,
            coreModule(enableLogging),
            mainModule,
        )
    }
}

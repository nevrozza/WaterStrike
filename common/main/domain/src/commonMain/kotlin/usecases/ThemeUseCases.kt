package usecases

import repository.MainRepository
import usecases.theme.GetThemeUseCase
import usecases.theme.GetThemeUseCaseImpl
import usecases.theme.SaveThemeUseCase
import usecases.theme.SaveThemeUseCaseImpl
import view.ThemeTint

interface ThemeUseCases {
    val getTheme: GetThemeUseCase
    val saveTheme: SaveThemeUseCase
}

class ThemeUseCasesImpl(
    repository: MainRepository
): ThemeUseCases {
    override val getTheme: GetThemeUseCase = GetThemeUseCaseImpl(repository)
    override val saveTheme: SaveThemeUseCase = SaveThemeUseCaseImpl(repository)

}
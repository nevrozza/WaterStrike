package usecases.theme

import repository.MainRepository
import view.ThemeTint

interface SaveThemeUseCase {
    operator fun invoke(theme: ThemeTint)
}

class SaveThemeUseCaseImpl(
    private val mainRepository: MainRepository
) : SaveThemeUseCase {
    override fun invoke(theme: ThemeTint) = mainRepository.saveTheme(theme)
}
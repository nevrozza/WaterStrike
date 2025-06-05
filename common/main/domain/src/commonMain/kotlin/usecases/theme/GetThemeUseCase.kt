package usecases.theme

import repository.MainRepository
import view.ThemeTint

interface GetThemeUseCase {
    operator fun invoke(): ThemeTint
}

class GetThemeUseCaseImpl(
    private val mainRepository: MainRepository
) : GetThemeUseCase {
    override fun invoke(): ThemeTint = mainRepository.getTheme()
}
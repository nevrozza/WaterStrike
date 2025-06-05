package usecases.nickname

import repository.MainRepository

interface SaveNicknameUseCase {
    operator fun invoke(nickname: String)
}

class SaveNicknameUseCaseImpl(
    private val mainRepository: MainRepository
): SaveNicknameUseCase {
    override fun invoke(
        nickname: String
    ) = mainRepository.saveNickname(nickname)
}
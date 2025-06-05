package usecases.nickname

import repository.MainRepository

interface GetNicknameUseCase {
    operator fun invoke(): String
}

class GetNicknameUseCaseImpl(
    private val mainRepository: MainRepository
) : GetNicknameUseCase {
    override fun invoke(): String = mainRepository.getNickname()
}
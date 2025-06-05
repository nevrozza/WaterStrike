package usecases

import repository.MainRepository
import usecases.nickname.GetNicknameUseCase
import usecases.nickname.GetNicknameUseCaseImpl
import usecases.nickname.SaveNicknameUseCase
import usecases.nickname.SaveNicknameUseCaseImpl

interface NicknameUseCases {
    val getNickname: GetNicknameUseCase
    val saveNickname: SaveNicknameUseCase
}

class NicknameUseCasesImpl(
    repository: MainRepository
) : NicknameUseCases {
    override val getNickname = GetNicknameUseCaseImpl(repository)
    override val saveNickname = SaveNicknameUseCaseImpl(repository)
}
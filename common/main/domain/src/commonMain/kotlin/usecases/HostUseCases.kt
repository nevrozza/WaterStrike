package usecases

import repository.MainRepository
import usecases.host.GetHostUseCase
import usecases.host.GetHostUseCaseImpl
import usecases.host.SaveHostUseCase
import usecases.host.SaveHostUseCaseImpl

interface HostUseCases {
    val getHost: GetHostUseCase
    val saveHost: SaveHostUseCase
}

class HostUseCasesImpl(
    repository: MainRepository
) : HostUseCases {
    override val getHost: GetHostUseCase = GetHostUseCaseImpl(repository)
    override val saveHost: SaveHostUseCase = SaveHostUseCaseImpl(repository)
}
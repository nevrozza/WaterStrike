package usecases.host

import repository.MainRepository

interface SaveHostUseCase {
    operator fun invoke(host: String)
}

class SaveHostUseCaseImpl(
    private val mainRepository: MainRepository
): SaveHostUseCase {
    override fun invoke(
        host: String
    ) = mainRepository.saveHost(host)
}
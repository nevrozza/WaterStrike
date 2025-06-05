package usecases.host

import repository.MainRepository

interface GetHostUseCase {
    operator fun invoke(): String
}

class GetHostUseCaseImpl(
    private val mainRepository: MainRepository
) : GetHostUseCase {
    override fun invoke(): String = mainRepository.getHost()
}
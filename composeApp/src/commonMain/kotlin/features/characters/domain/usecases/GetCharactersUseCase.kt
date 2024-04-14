package features.characters.domain.usecases

import features.characters.domain.repositories.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getCharactersPagingFlow()
}
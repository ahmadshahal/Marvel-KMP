package features.characters.domain.usecases

import features.characters.domain.repositories.CharactersRepository

class GetCharacterUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: Int) = charactersRepository.getCharacter(id)
}
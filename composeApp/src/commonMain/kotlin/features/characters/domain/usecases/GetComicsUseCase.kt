package features.characters.domain.usecases

import features.characters.domain.repositories.CharactersRepository

class GetComicsUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getComics(characterId)
}
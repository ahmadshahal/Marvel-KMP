package features.characters.domain.usecases

import features.characters.domain.repositories.CharactersRepository

class GetSeriesUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getSeries(characterId)
}
package features.characters.domain.usecases

import features.characters.domain.repositories.CharactersRepository

class GetEventsUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getEvents(characterId)
}
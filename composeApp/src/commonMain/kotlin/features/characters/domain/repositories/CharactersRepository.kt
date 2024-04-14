package features.characters.domain.repositories

import androidx.paging.PagingData
import features.characters.domain.entities.Character
import features.characters.domain.entities.publications.Comic
import features.characters.domain.entities.publications.Event
import features.characters.domain.entities.publications.Serie
import features.characters.domain.entities.publications.Story
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacter(id: Int): Result<Character>

    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getComics(characterId: Int): Result<List<Comic>>

    suspend fun getEvents(characterId: Int): Result<List<Event>>

    suspend fun getSeries(characterId: Int): Result<List<Serie>>

    suspend fun getStories(characterId: Int): Result<List<Story>>

    suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>>
}
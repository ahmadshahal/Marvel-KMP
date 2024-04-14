package features.characters.data.remote.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import features.characters.data.remote.api.CharactersApi
import features.characters.data.remote.mappers.toCharacter
import features.characters.data.remote.mappers.toComic
import features.characters.data.remote.mappers.toEvent
import features.characters.data.remote.mappers.toSerie
import features.characters.data.remote.mappers.toStory
import features.characters.data.remote.paging.CharactersPagingSource
import features.characters.domain.entities.Character
import features.characters.domain.repositories.CharactersRepository
import core.data.base.repositories.BaseRemoteRepository
import core.data.env.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi
) : BaseRemoteRepository(), CharactersRepository {
    override suspend fun getCharacter(id: Int) = runCatching {
        charactersApi.getCharacter(id).toCharacter()
    }

    override suspend fun getCharacters() = runCatching {
        charactersApi.getCharacters().map { it.toCharacter() }
    }

    override suspend fun getComics(characterId: Int) = runCatching {
        charactersApi.getComics(characterId).map { it.toComic() }
    }

    override suspend fun getEvents(characterId: Int) = runCatching {
        charactersApi.getEvents(characterId).map { it.toEvent() }
    }

    override suspend fun getStories(characterId: Int) = runCatching {
        charactersApi.getStories(characterId).map { it.toStory() }
    }

    override suspend fun getSeries(characterId: Int) = runCatching {
        charactersApi.getSeries(characterId).map { it.toSerie() }
    }

    override suspend fun getCharactersPagingFlow(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                CharactersPagingSource(charactersApi)
            }
        ).flow.map { it.map { characterDto -> characterDto.toCharacter() } }
    }
}
package features.characters.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import core.data.env.NETWORK_PAGE_SIZE
import features.characters.data.remote.api.CharactersApi
import features.characters.data.remote.models.CharacterDto

class CharactersPagingSource(
    private val charactersApi: CharactersApi,
) : PagingSource<Int, CharacterDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val currentPage = params.key ?: 1
        val result = runCatching {
            charactersApi.getCharacters(
                skip = currentPage * NETWORK_PAGE_SIZE,
                limit = NETWORK_PAGE_SIZE
            )
        }
        return result.fold(
            onSuccess = { characters ->
                LoadResult.Page(
                    data = characters,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (characters.isEmpty()) null else currentPage + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition
    }
}
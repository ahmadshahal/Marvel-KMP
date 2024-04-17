package features.characters.ui.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import features.characters.domain.entities.Character
import features.characters.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ScreenModel {

    private val _charactersState: MutableStateFlow<PagingData<Character>> = MutableStateFlow(value = PagingData.empty())
    val charactersState: StateFlow<PagingData<Character>> get() = _charactersState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        screenModelScope.launch {
            getCharactersUseCase()
                .distinctUntilChanged()
                .cachedIn(screenModelScope)
                .collect {
                    _charactersState.emit(it)
                }
        }
    }
}
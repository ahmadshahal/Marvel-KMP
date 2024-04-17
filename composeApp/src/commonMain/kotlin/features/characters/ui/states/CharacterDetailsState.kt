package features.characters.ui.states

import core.ui.states.FetchState
import features.characters.domain.entities.Character
import features.characters.domain.entities.publications.Comic
import features.characters.domain.entities.publications.Event
import features.characters.domain.entities.publications.Serie
import features.characters.domain.entities.publications.Story
import features.characters.domain.enums.PublicationType

data class CharacterDetailsState(
    val fetchState: FetchState<CharacterDetails> = FetchState.Initial(),
    val selectedPublicationType: PublicationType = PublicationType.COMICS,
)

data class CharacterDetails(
    val character: Character,
    val comics: List<Comic>,
    val events: List<Event>,
    val series: List<Serie>,
    val stories: List<Story>
)
package features.characters.di

import features.characters.data.remote.api.CharactersApi
import features.characters.data.remote.repositories.CharactersRepositoryImpl
import features.characters.domain.repositories.CharactersRepository
import features.characters.domain.usecases.GetCharacterUseCase
import features.characters.domain.usecases.GetCharactersUseCase
import features.characters.domain.usecases.GetComicsUseCase
import features.characters.domain.usecases.GetEventsUseCase
import features.characters.domain.usecases.GetSeriesUseCase
import features.characters.domain.usecases.GetStoriesUseCase
import org.koin.dsl.bind
import org.koin.dsl.module

val CharactersModule = module {
    factory { CharactersApi(get()) }
    factory { CharactersRepositoryImpl(get()) } bind CharactersRepository::class
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
    factory { GetComicsUseCase(get()) }
    factory { GetEventsUseCase(get()) }
    factory { GetSeriesUseCase(get()) }
    factory { GetStoriesUseCase(get()) }
    // viewModel { CharactersViewModel(get()) }
    // viewModel { CharacterDetailsViewModel(get(), get(), get(), get(), get(), get()) }
}
package app.di

import core.di.NetworkModule
import features.characters.di.CharactersModule

fun appModule() = listOf(NetworkModule, CharactersModule)
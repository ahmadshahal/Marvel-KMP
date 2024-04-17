package app.ui

import androidx.compose.runtime.Composable
import app.ui.theme.MarvelTheme
import cafe.adriel.voyager.navigator.Navigator
import features.characters.ui.screens.CharactersScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MarvelTheme {
        Navigator(CharactersScreen)
    }
}
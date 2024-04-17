package app.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import app.ui.theme.MarvelTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import features.characters.ui.screens.CharactersScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MarvelTheme {
        Navigator(CharactersScreen) {
            FadeTransition(
                navigator = it,
                animationSpec = spring(stiffness = Spring.StiffnessLow)
            )
        }
    }
}
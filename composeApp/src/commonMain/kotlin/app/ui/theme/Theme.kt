package app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = Red,
    background = White,
    onBackground = DarkGray,
    secondary = Gray,
    onPrimary = White,
    surface = White,
    onSurface = DarkGray,
    surfaceContainer = DarkGray,
    onPrimaryContainer = White
)

@Composable
fun MarvelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = marvelTypography(),
        content = content
    )
}
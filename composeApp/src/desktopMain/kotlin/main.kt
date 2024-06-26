import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.ui.App

fun main() = application {
    init()
    Window(onCloseRequest = ::exitApplication, title = "Marvel") {
        App()
    }
}
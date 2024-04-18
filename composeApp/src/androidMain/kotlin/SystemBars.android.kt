import com.kotlinhero.marvel.MainActivity
import com.kotlinhero.marvel.utils.enableDarkEdgeToEdge
import com.kotlinhero.marvel.utils.enableLightEdgeToEdge

actual fun enableLightEdgeToEdge() {
    MainActivity.context?.enableLightEdgeToEdge()
}

actual fun enableDarkEdgeToEdge() {
    MainActivity.context?.enableDarkEdgeToEdge()
}

import app.di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

fun init() {
    Napier.base(DebugAntilog())
    startKoin {
        modules(appModule())
    }
}
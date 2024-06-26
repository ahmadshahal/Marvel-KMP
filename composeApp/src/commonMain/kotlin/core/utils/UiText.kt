package core.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    class StringResource @OptIn(ExperimentalResourceApi::class) constructor(
        val stringResource: org.jetbrains.compose.resources.StringResource,
        vararg val args: Any
    ): UiText()

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(stringResource, *args)
        }
    }
}
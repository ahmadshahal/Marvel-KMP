package core.ui.states

import core.utils.UiText

data class FormValueState<T>(
    val value: T,
    val isValid: Boolean = true,
    val validationMessage: UiText? = null,
)

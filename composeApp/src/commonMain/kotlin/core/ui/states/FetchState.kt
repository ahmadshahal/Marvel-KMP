package core.ui.states

import core.utils.UiText


sealed class FetchState<T>(val data: T? = null) {
    class Success<T>(data: T) : FetchState<T>(data)
    class Error<T>(val message: UiText) : FetchState<T>()
    class Loading<T> : FetchState<T>()
    class Initial<T> : FetchState<T>()
}

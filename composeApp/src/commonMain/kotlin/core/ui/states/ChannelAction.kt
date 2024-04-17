package core.ui.states

import core.utils.UiText


sealed class ChannelAction {
    // class Navigate(val navigationAction: (NavController) -> Unit) : ChannelAction()
    class ShowSnackBar(val uiText: UiText) : ChannelAction()
}
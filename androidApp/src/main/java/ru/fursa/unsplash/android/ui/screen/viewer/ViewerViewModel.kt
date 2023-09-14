package ru.fursa.unsplash.android.ui.screen.viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.fursa.unsplash.android.base.ImageDownloader

class ViewerViewModel(
    private val imageDownloader: ImageDownloader
) : ViewModel() {
    private val _sideEffect = Channel<ViewerEvent.SideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun obtainEvent(event: ViewerEvent) {
        when (event) {
            is ViewerEvent.Loading -> {
                postSideEffect(ViewerEvent.SideEffect.OnStartLoading)
            }
            is ViewerEvent.OnSuccess -> {
                postSideEffect(ViewerEvent.SideEffect.OnFinishLoading)
            }
            is ViewerEvent.OnSetWallpaper -> {
                setWallpaper(event.url)
            }
            is ViewerEvent.OnError -> {
                postSideEffect(ViewerEvent.SideEffect.OnErrorLoading)
            }
            else -> Unit
        }
    }

    private fun setWallpaper(url: String) {
        viewModelScope.launch {
            imageDownloader.setWallpaper(url)

        }
    }

    private fun postSideEffect(effect: ViewerEvent.SideEffect) {
        viewModelScope.launch {
            _sideEffect.send(effect)
        }
    }

    sealed class ViewerEvent {
        object Loading : ViewerEvent()
        object OnSuccess : ViewerEvent()
        data class OnSetWallpaper(val url: String) : ViewerEvent()
        data class OnError(val error: String) : ViewerEvent()

        open class SideEffect : ViewerEvent() {
            object OnSuccessSetWallpaper : SideEffect()
            object OnErrorLoading : SideEffect()
            object OnStartLoading: SideEffect()
            object OnFinishLoading: SideEffect()
        }
    }
}

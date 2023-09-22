package ru.fursa.unsplash.android.ui.screen.viewer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewerViewModel(private val context: Context) : ViewModel() {
    sealed class ImageLoadState {
        object Idle : ImageLoadState()
        object Loading : ImageLoadState()

        data class ErrorLoading(val message: String) : ImageLoadState()
        data class Success(val result: SuccessResult) : ImageLoadState()
    }

    private val _viewState = MutableStateFlow<ImageLoadState>(ImageLoadState.Idle)
    val viewState = _viewState.asStateFlow()

    fun loadImage(url: String, loader: ImageLoader) {
        viewModelScope.launch {

            val request = ImageRequest.Builder(context = context)
                .data(url)
                .listener(
                    onStart = {
                        _viewState.value = ImageLoadState.Loading
                    },
                    onSuccess = { _, res ->
                        _viewState.value = ImageLoadState.Success(res)
                    }, onError = { _, res ->
                    _viewState.value = ImageLoadState.ErrorLoading(
                        res.throwable.message.toString()
                    )
                }
                )
                .build()
            loader.execute(request)
        }
    }
}

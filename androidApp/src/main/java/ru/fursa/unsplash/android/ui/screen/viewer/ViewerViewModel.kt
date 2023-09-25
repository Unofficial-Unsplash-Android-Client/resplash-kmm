package ru.fursa.unsplash.android.ui.screen.viewer

import android.app.WallpaperManager
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.fursa.unsplash.android.R
import ru.fursa.unsplash.android.base.mvi.MVIBaseViewModel

class ViewerViewModel(
    private val imageRequest: ImageRequest.Builder,
    private val loader: ImageLoader.Builder,
    private val wallpaperManager: WallpaperManager,
) : MVIBaseViewModel<ViewerMVIContract.Event, ViewerMVIContract.State, ViewerMVIContract.Effect>() {
    override fun createInitialState(): ViewerMVIContract.State {
        return ViewerMVIContract.State(
            isSetWallpaperButtonVisible = false,
            isError = false,
            isLoading = true,
            errorMessage = "",
            pictureDrawable = null,
            isSetWallpaperClicked = false,
        )
    }

    override fun handleEvent(event: ViewerMVIContract.Event) {
        when (event) {
            ViewerMVIContract.Event.Loading -> {
                setState {
                    copy(
                        isSetWallpaperButtonVisible = false,
                        isLoading = true,
                        errorMessage = "",
                        isError = false,
                    )
                }
            }

            is ViewerMVIContract.Event.Success -> {
                setState {
                    copy(
                        isSetWallpaperButtonVisible = true,
                        isLoading = false,
                        errorMessage = "",
                        isError = false,
                        isSuccess = true,
                        pictureDrawable = event.data.drawable
                    )
                }
            }

            is ViewerMVIContract.Event.Error -> {
                setState {
                    copy(
                        isSetWallpaperButtonVisible = false,
                        isLoading = false,
                        errorMessage = event.message,
                        isError = true,
                    )
                }
            }

            is ViewerMVIContract.Event.OnClickSetWallpaper -> {
                setState {
                    copy(isSetWallpaperClicked = true, wallpaperUrl = event.url)
                }
                setupWallpaper(event.url)
                setEffect {
                    ViewerMVIContract.Effect.ShowToastMessage(R.string.set_wallpaper_message)
                }
            }

            else -> Unit
        }
    }

    private fun setupWallpaper(url: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val request = imageRequest.data(url).build()
            val bitmap = loader.build().execute(request).drawable?.toBitmap()
            wallpaperManager.setBitmap(bitmap)
        }
    }
}

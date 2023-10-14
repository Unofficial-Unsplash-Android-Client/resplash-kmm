package ru.fursa.unsplash.android.ui.screen.viewer

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import coil.request.SuccessResult
import ru.fursa.unsplash.android.base.mvi.UiEffect
import ru.fursa.unsplash.android.base.mvi.UiEvent
import ru.fursa.unsplash.android.base.mvi.UiState

class ViewerMVIContract {

    sealed class Event : UiEvent {
        object Loading : Event()
        data class Error(val message: String) : Event()
        data class Success(val data: SuccessResult) : Event()
        data class OnClickSetWallpaper(val url: String) : Event()
        data class OnInfoClick(val id: String) : Event()
    }

    data class State(
        val isSetWallpaperButtonVisible: Boolean = false,
        val isError: Boolean = false,
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val watchCount: Long = 0,
        val downloadsCount: Long = 0,
        val likesCount: Long = 0,
        val isShowInfoDialog: Boolean = false,
        val isSetWallpaperClicked: Boolean = false,
        val wallpaperUrl: String = "",
        val errorMessage: String = "",
        val pictureDrawable: Drawable? = null,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToastMessage(@StringRes val message: Int) : Effect()
    }
}

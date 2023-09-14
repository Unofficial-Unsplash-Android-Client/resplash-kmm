package ru.fursa.unsplash.android.base

import android.app.WallpaperManager
import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.ImageRequest

class ImageDownloader(
    private val context: Context,
    private val wallpaperManager: WallpaperManager,
) {

    suspend fun setWallpaper(url: String) {
        val request = ImageRequest.Builder(context).data(url).build()
        val bitmap = Coil.imageLoader(context).execute(request).drawable?.toBitmap()
        wallpaperManager.setBitmap(bitmap)
    }
}

package ru.fursa.unsplash.base.utils

import android.content.Context
import androidx.annotation.StringRes
import ru.fursa.unsplash.utils.ResourceProvider

class AndroidResourceProvider(
    private val context: Context
): ResourceProvider {
    override fun getString(@StringRes stringId: Int): String {
        return context.getString(stringId)
    }
}
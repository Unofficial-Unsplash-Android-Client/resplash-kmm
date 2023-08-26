package ru.fursa.unsplash.base.utils

import android.content.Context
import androidx.annotation.StringRes

class AndroidResourceProvider(
    private val context: Context
): ResourceProvider {
    override fun getString(@StringRes stringId: Int): String {
        return context.getString(stringId)
    }
}
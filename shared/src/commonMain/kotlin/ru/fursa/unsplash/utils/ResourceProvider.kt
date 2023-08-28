package ru.fursa.unsplash.utils

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}

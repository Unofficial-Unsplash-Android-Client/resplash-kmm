package ru.fursa.unsplash.base.utils

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
}
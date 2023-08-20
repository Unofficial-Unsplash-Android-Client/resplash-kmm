package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val urls: Urls,
)
package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoverPhoto(
    @SerialName("urls") val urls: Urls
)

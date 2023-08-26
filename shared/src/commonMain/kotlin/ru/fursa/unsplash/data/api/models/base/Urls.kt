package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    @SerialName("raw") val rawUrl: String,
    @SerialName("full") val fullUrl: String,
    @SerialName("regular") val regularUrl: String,
    @SerialName("small") val smallUrl: String,
    @SerialName("thumb") val thumbUrl: String
)

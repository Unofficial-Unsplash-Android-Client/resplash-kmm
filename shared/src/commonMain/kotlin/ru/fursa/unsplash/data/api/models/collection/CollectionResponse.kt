package ru.fursa.unsplash.data.api.models.collection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerialName("total_photos")
    val totalPhotos: Int,
)
@Serializable
data class CoverPhoto(
    @SerialName("urls") val urls: Urls)


@Serializable
data class Urls(
    @SerialName("raw") val rawUrl: String,
    @SerialName("full") val fullUrl: String,
    @SerialName("regular") val regularUrl: String,
    @SerialName("small") val smallUrl: String,
    @SerialName("thumb") val thumbUrl: String
)

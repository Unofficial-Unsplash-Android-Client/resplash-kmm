package ru.fursa.unsplash.data.api.models.collection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.base.CoverPhoto

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

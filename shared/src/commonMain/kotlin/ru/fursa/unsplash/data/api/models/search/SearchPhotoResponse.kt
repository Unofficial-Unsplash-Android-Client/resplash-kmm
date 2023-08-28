package ru.fursa.unsplash.data.api.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

@Serializable
data class SearchPhotoResponse(
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<PhotoResponse>,
)

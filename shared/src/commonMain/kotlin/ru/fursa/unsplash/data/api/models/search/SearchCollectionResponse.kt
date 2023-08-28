package ru.fursa.unsplash.data.api.models.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.collection.CollectionResponse

@Serializable
data class SearchCollectionResponse(
    val results: List<CollectionResponse>,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int
)

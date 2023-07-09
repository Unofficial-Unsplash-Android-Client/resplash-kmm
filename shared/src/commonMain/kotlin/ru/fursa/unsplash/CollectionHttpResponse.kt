package ru.fursa.unsplash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionHttpResponse(
    @SerialName("id")
    val id: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
)
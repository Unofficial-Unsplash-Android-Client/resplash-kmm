package ru.fursa.unsplash.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatResponse(
    @SerialName("downloads")
    val downloads: Downloads,
    @SerialName("likes")
    val likes: Likes,
    @SerialName("views")
    val views: Views
)

@Serializable
data class Downloads(
    val total: Long
)
@Serializable
data class Likes(
    val total: Long,
)

@Serializable
data class Views(
    val total: Long
)

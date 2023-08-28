package ru.fursa.unsplash.data.api.models.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.base.Urls
import ru.fursa.unsplash.data.api.models.base.User

@Serializable
data class PhotoResponse(
    val id: String,
    @SerialName("user")
    val user: User?,
    @SerialName("urls")
    val urls: Urls,
    @SerialName("width")
    val width: Int?,
    @SerialName("height")
    val height: Int?,
)

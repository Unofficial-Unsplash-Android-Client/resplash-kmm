package ru.fursa.unsplash.data.api.models.search

import kotlinx.serialization.Serializable
import ru.fursa.unsplash.data.api.models.base.User

@Serializable
data class UserSearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<User>
)

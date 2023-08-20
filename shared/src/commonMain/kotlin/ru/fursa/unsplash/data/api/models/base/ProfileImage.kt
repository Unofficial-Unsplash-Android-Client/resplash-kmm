package ru.fursa.unsplash.data.api.models.base

import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(
    val small: String,
    val medium: String,
)
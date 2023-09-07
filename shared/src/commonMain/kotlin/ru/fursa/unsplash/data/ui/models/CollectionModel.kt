package ru.fursa.unsplash.data.ui.models

data class CollectionModel(
    val collectionId: String,
    val coverPhotoUrl: String,
    val title: String,
    val totalPhotos: Int,
    val authorName: String,
)

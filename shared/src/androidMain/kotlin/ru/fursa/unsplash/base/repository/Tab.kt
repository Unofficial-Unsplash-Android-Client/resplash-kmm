package ru.fursa.unsplash.base.repository

data class Tab(val name: String, val type: TabType)

enum class TabType {
    PHOTO, COLLECTION, LIKE
}
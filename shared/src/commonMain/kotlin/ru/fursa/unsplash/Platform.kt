package ru.fursa.unsplash

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
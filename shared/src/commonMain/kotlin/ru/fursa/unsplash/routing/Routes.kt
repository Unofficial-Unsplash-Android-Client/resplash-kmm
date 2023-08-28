package ru.fursa.unsplash.routing

sealed class Routes(val name: String) {
    object Home: Routes(name = "home")
    object Collections: Routes(name = "collections")
    object Start: Routes(name = "start")
    object Profile: Routes(name = "profile")
    object Authorization: Routes(name = "authorization")
    object Registration: Routes(name = "registration")
    object Reset: Routes(name = "reset")
    object PhotoDetail: Routes(name = "photo_detail")
    object Search: Routes(name = "search")
    object Error: Routes(name = "error")
}
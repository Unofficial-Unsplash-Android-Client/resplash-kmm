package ru.fursa.unsplash.android.ui.screen.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.fursa.unsplash.android.ui.screen.authorization.AuthorizationScreen
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.detals.PhotoDetailScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.screen.profile.ProfileScreen
import ru.fursa.unsplash.android.ui.screen.registration.RegistrationScreen
import ru.fursa.unsplash.android.ui.screen.reset.ResetPasswordScreen
import ru.fursa.unsplash.android.ui.screen.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "start") {
        composable("home") {
            HomeScreen()
        }

        composable("collections") {
            CollectionPhotoScreen()
        }

        composable(route = "profile") {
            ProfileScreen(navController = navController)
        }

        composable(route = "start") {
            EmptyScreen()
        }

        composable(route = "authorization") {
            AuthorizationScreen(navController = navController)
        }
        
        composable(route = "registration") {
            RegistrationScreen(navController = navController)
        }

        composable(route = "reset") {
            ResetPasswordScreen(navController = navController)
        }

        composable(route = "photo_detail") {
            PhotoDetailScreen(navController = navController)
        }
        
        composable(route = "search") {
            SearchScreen(navController = navController)
        }
    }
}

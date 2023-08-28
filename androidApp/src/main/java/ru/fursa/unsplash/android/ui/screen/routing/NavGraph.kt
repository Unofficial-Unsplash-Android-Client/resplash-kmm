package ru.fursa.unsplash.android.ui.screen.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.fursa.unsplash.android.base.screen.EmptyScreen
import ru.fursa.unsplash.android.base.screen.ErrorScreen
import ru.fursa.unsplash.android.ui.screen.authorization.AuthorizationScreen
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.detals.PhotoDetailScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.screen.profile.ProfileScreen
import ru.fursa.unsplash.android.ui.screen.registration.RegistrationScreen
import ru.fursa.unsplash.android.ui.screen.reset.ResetPasswordScreen
import ru.fursa.unsplash.android.ui.screen.search.SearchScreen
import ru.fursa.unsplash.routing.Routes

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Start.name) {
        composable(Routes.Home.name) {
            HomeScreen()
        }

        composable(Routes.Collections.name) {
            CollectionPhotoScreen()
        }

        composable(
            route = "${Routes.Profile.name}/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) {
            ProfileScreen(
                username = it.arguments?.getString("username").orEmpty(),
                navController = navController
            )
        }

        composable(route = Routes.Start.name) {
            EmptyScreen()
        }

        composable(route = Routes.Error.name) {
            ErrorScreen()
        }

        composable(route = Routes.Authorization.name) {
            AuthorizationScreen(navController = navController)
        }

        composable(route = Routes.Registration.name) {
            RegistrationScreen(navController = navController)
        }

        composable(route = Routes.Reset.name) {
            ResetPasswordScreen(navController = navController)
        }

        composable(route = Routes.PhotoDetail.name) {
            PhotoDetailScreen(navController = navController)
        }

        composable(route = Routes.Search.name) {
            SearchScreen(navController = navController)
        }
    }
}

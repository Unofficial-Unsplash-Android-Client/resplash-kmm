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
import ru.fursa.unsplash.android.ui.screen.collection.CollectionScreen
import ru.fursa.unsplash.android.ui.screen.collections.CollectionPhotoScreen
import ru.fursa.unsplash.android.ui.screen.detals.PhotoDetailScreen
import ru.fursa.unsplash.android.ui.screen.home.HomeScreen
import ru.fursa.unsplash.android.ui.screen.profile.ProfileScreen
import ru.fursa.unsplash.android.ui.screen.registration.RegistrationScreen
import ru.fursa.unsplash.android.ui.screen.reset.ResetPasswordScreen
import ru.fursa.unsplash.android.ui.screen.search.SearchScreen
import ru.fursa.unsplash.android.ui.screen.viewer.ViewScreen
import ru.fursa.unsplash.routing.Routes

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Start.name) {
        composable(Routes.Home.name) {
            HomeScreen(navController = navController)
        }

        composable(Routes.Collections.name) {
            CollectionPhotoScreen(username = "")
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

        composable(
            route = "${Routes.View.name}/{url}/{username}/{avatarUrl}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("avatarUrl") { type = NavType.StringType },
                navArgument("username") { type = NavType.StringType }
            )
        ) {
            ViewScreen(
                url = it.arguments?.getString("url").orEmpty(),
                avatarUrl = it.arguments?.getString("avatarUrl").orEmpty(),
                username = it.arguments?.getString("username").orEmpty(),
                navController = navController
            )
        }

        composable(route = Routes.Start.name) {
            EmptyScreen()
        }

        composable(route = Routes.Error.name) {
            ErrorScreen(message = "")
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

        composable(
            route = "${Routes.Collection.name}/{id}/{author_name}/{photo_count}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                },
                navArgument("author_name") {
                    type = NavType.StringType
                },
                navArgument("photo_count") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            CollectionScreen(
                navController = navController,
                id = navBackStackEntry.arguments?.getString("id").orEmpty(),
                authorName = navBackStackEntry.arguments?.getString("author_name").orEmpty(),
                photoCount = navBackStackEntry.arguments?.getInt("photo_count") ?: 0
            )
        }

        composable(route = Routes.Search.name) {
            SearchScreen(navController = navController)
        }
    }
}

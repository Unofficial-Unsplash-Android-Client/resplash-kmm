package ru.fursa.unsplash.android.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.base.encodeUrl
import ru.fursa.unsplash.android.ui.kit.compound.Screen
import ru.fursa.unsplash.android.ui.kit.list.BuildHomeList
import ru.fursa.unsplash.routing.Routes

@Composable
fun UserLikesScreen(
    username: String,
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {
    Screen(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val photos = viewModel.userLikedPhotos.collectAsLazyPagingItems()

        BuildHomeList(
            photos = photos,
            onNavigateClick = { username ->
                navController.navigate("${Routes.Profile.name}/$username")
            }, onViewPhoto = { photoModel ->
            navController.navigate(
                "${Routes.View.name}/${photoModel.photoUrl.encodeUrl()}" +
                    "/$username/${photoModel.profileImage.encodeUrl()}"
            )
        }
        )

        LaunchedEffect(key1 = username, block = {
            viewModel.getUserLikedPhotos(username)
        })
    }
}

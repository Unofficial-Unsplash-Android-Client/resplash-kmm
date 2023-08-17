package ru.fursa.unsplash.android.ui.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.controls.photo.PhotoCard
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.base.Photo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val photosPagingItems: LazyPagingItems<Photo> = viewModel
        .screenState.collectAsLazyPagingItems()

    NavGraph(navController = navController)
    CreateHomeList(
        navController = navController,
        photos = photosPagingItems
    )
}

@Composable
fun CreateHomeList(navController: NavController, photos: LazyPagingItems<Photo>) {
    LazyColumn {
        items(photos.itemCount) { index ->
            val item = photos[index] ?: return@items
            ListItem(photo = item, onClick = {
                navController.navigate("photo_detail")
            })
        }
    }
}

@Composable
fun ListItem(photo: Photo, onClick: (String) -> Unit) {
    PhotoCard(photo.photoUrl,
        photo.username,
        photo.profileImage,
        onAuthorClick = { onClick(photo.username) })
}


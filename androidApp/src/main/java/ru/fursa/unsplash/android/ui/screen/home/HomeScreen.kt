package ru.fursa.unsplash.android.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import ru.fursa.unsplash.android.ui.controls.photo.PhotoCard
import ru.fursa.unsplash.android.ui.screen.routing.NavGraph
import ru.fursa.unsplash.data.api.models.photo.PhotoResponse

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val photosPagingItems: LazyPagingItems<PhotoResponse> = viewModel
        .photosPager
        .collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }

    NavGraph(navController = navController)
    CreateHomeList(
        navController = navController,
        photos = photosPagingItems
    )
}

@Composable
fun CreateHomeList(navController: NavController, photos: LazyPagingItems<PhotoResponse>) {
    LazyColumn {
        items(count = photos.itemCount) { index ->
            Log.d("Unsplash app", "Total photos in page ${photos.itemCount}")
            val item = photos[index] ?: return@items
            ListItem(photos = item, onClick = {
                navController.navigate("photo_detail")
            })
        }
    }
}

@Composable
fun ListItem(photos: PhotoResponse, onClick: (String) -> Unit) {
    PhotoCard(photos.urls.regular,
        photos.user.name,
        photos.user.profileImage.medium,
        onAuthorClick = { onClick(photos.user.name) })
}

